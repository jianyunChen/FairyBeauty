package com.fairyBeauty.aop;


import com.alibaba.fastjson.JSON;
import com.fairyBeauty.entity.MUser;
import com.fairyBeauty.entity.TAction;
import com.fairyBeauty.entity.base.WebResponseVo;
import com.fairyBeauty.entity.vo.UserRoleList;
import com.fairyBeauty.enums.ActionTypeEnum;
import com.fairyBeauty.enums.RedisKeysEnum;
import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.enums.TokenExistEnum;
import com.fairyBeauty.mapper.TActionMapper;
import com.fairyBeauty.service.MUserService;
import com.fairyBeauty.utils.IpUtils;
import com.fairyBeauty.utils.RedisUtils;
import com.fairyBeauty.utils.TokenUtils;
import com.fairyBeauty.utils.WebResponseException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * action提交日志记录
 *
 * @Author chenjianyun
 * @Date 2021/3/24 17:17
 * @Description action提交日志记录
 */
@Component
@Aspect
public class AccountServiceImpl {
    @Resource
    private TActionMapper actionMapper;
    private static final String TRACE_ID = "traceId";
    private static final String URL_WITH_QUERY_STRING = "urlWithQueryString";
    @Autowired
    private RedisUtils redisUtils;
    @Value("${checkLogin}")
    private Boolean checkLogin;
    @Resource
    private MUserService userService;

    /**
     * controller方法切面，
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.fairyBeauty.controller.*.*(..))")
    public Object run1(ProceedingJoinPoint joinPoint) throws Throwable {
        Long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        //解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,Token,userId,checkCookies,Last-Modified");
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        if (checkLogin && !"OPTIONS".equals(request.getMethod())) {
            String token = request.getHeader("AccessToken");
            String userId = request.getHeader("userId");
            //获取用户信息
            UserRoleList userRoleList = (UserRoleList)session.getAttribute("userRoleList");
            String role = (String)session.getAttribute("role");

            //token验证
            if(StringUtils.isEmpty(token)){
                return new WebResponseVo(ResponseEnum.TOKEN_ERROR_TIMEOUT);
            }else{
                if(TokenExistEnum.REST.equals(TokenUtils.verify(userId,token))){
                    //获取用户信息
                    MUser userInfo = userService.getUserByUserId(userId);
                    if(userInfo == null){
                        throw new WebResponseException(ResponseEnum.USER_NULL);
                    }
                    userService.getLoginInfo(request,response,userInfo,userRoleList,token);
                }
            }
            //用户权限判空验证
            if(userRoleList == null && redisUtils.get(userId + "userRoleList") == null){
                return new WebResponseVo(ResponseEnum.USER_ROLELIST_NULL);
            }else{
                userRoleList = JSON.parseObject((String)redisUtils.get(userId + "userRoleList"), UserRoleList.class);
            }
            //用户验证
            if(userRoleList.getUser() == null || StringUtils.isEmpty(userRoleList.getUser().getUserId()) ){
                return new WebResponseVo(ResponseEnum.USER_NULL);
            }

            //刷新用户最后操作时间
            redisUtils.set(RedisKeysEnum.USER_LASTER_UPDATETIME.getKey(),System.currentTimeMillis(),RedisUtils.loginTime);
            //权限验证
            if(!userRoleList.getRoleList().stream().anyMatch(u-> role.equals(u.getRoleId()))){
                return new WebResponseVo(ResponseEnum.ROLE_ERROR);
            }
        }

        try {
            //获取方法参数值数组
            Object[] args = joinPoint.getArgs();
            //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
            Object result = joinPoint.proceed();

            return result;
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

    //切点在webpointCut()
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void webPointcut() {
    }
    //切点在getPointCut()
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void getPointCut() {
    }
    //切点在postPointCut()
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    private void postPointCut() {
    }

    @AfterThrowing(pointcut = "webPointcut()", throwing = "ex")
    public void webThrowing(JoinPoint joinPoint, Exception ex) {
        handleThrowing(joinPoint, ex);
    }

    @AfterThrowing(pointcut = "getPointCut()", throwing = "ex")
    public void getThrowing(JoinPoint joinPoint, Exception ex) {
        handleThrowing(joinPoint, ex);
    }

    @AfterThrowing(pointcut = "postPointCut()", throwing = "ex")
    public void postThrowing(JoinPoint joinPoint, Exception ex) {
        handleThrowing(joinPoint, ex);
    }

    /**
     * controller类抛出的异常在这边捕获
     */
    @AfterThrowing(pointcut = "webPointcut(),getPointCut(),postPointCut()", throwing = "ex")
    public void handleThrowing(JoinPoint joinPoint, Exception ex) {
        Long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader("userId");
        String userName = userId;

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        StringBuffer msg = new StringBuffer("").append("类【")
                .append(className)
                .append("】中的方法【")
                .append(methodName)
                .append("】发现异常【")
                .append(ex.getMessage())
                .append("】");
        TAction actionVo = new TAction(className + "." + methodName, ActionTypeEnum.ERROR_INFO, userId, userName, beginTime, msg.toString(), IpUtils.getIpAddr(request));
        //操作耗时
        Long time = System.currentTimeMillis() - beginTime;
        actionVo.setActionTimeConsuming(time.intValue());
        //新增异常日志
        actionMapper.insert(actionVo);
        String errorMsg = StringUtils.isEmpty(ex.getMessage()) ? "System Error！" : ex.getMessage();
        writeContent(errorMsg);
    }

    /**
     * 将内容输出到浏览器
     *
     * @param content 输出内容
     */
    private void writeContent(String content) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        try {
            OutputStream ps = response.getOutputStream();
            ps.write(content.getBytes("UTF-8"));
            ps.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取方法类全名+方法名
    private String getClassAndMethodName(MethodSignature method) {
        //获取类全名
        String className = method.getMethod().getDeclaringClass().getName();
        //获取方法名
        String methodName = method.getName();
        return new StringBuffer(className).append(".").append(methodName).toString();
    }
}

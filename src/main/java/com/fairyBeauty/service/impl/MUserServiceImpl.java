package com.fairyBeauty.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fairyBeauty.entity.*;
import com.fairyBeauty.entity.vo.UserRoleList;
import com.fairyBeauty.enums.ActionTypeEnum;
import com.fairyBeauty.enums.RedisKeysEnum;
import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.mapper.MUserMapper;
import com.fairyBeauty.service.*;
import com.fairyBeauty.utils.*;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fairyBeauty.utils.CookieUtils.cookiekeys;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

    @Resource
    private MUserRoleService userRoleService;
    @Resource
    private MMenuService menuService;
    @Resource
    private MRoleService roleService;
    @Resource
    private PCompanyService companyService;
    @Resource
    private TActionService actionService;
    @Resource
    private RedisUtils redisUtils;

    @Override
    public UserRoleList signIn(MUser user) {
        UserRoleList userRoleList = new UserRoleList();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        Long beginTime = System.currentTimeMillis();
        //账号解码
        String userId = BasicUtil.encodedBase64(user.getUserId());
        //密码解密后加密
        String passWord = DESUtil.encrypt(BasicUtil.encodedBase64(user.getUserPassword()));
        MUser userInfo = baseMapper.getUserByIdAndPassword(userId,passWord);
        if(userInfo == null){
            throw new WebResponseException(ResponseEnum.USER_ERROR);
        }
        String className = request.getServletPath();
        String methodName = request.getMethod();
        String ip = IpUtils.getIpAddr(attributes.getRequest());
        StringBuffer msg = new StringBuffer("").append("用户【")
                .append(userInfo.getUserName())
                .append("】在【")
                .append(DateUtils.getDateTimeStringOfTimestamp(beginTime))
                .append("】通过")
                .append(ip)
                .append("】登录系统。");
        TAction actionVo = new TAction(className, ActionTypeEnum.LOGIN_IN, userId, userInfo.getUserName(), beginTime, msg.toString(), ip);
        Long time = System.currentTimeMillis() - beginTime;
        actionVo.setActionTimeConsuming(time.intValue());
        //设置用户
        userRoleList.setUser(userInfo);

        //插入token信息
        String token = "";
        //判断是否已登录过，已登录的直接返回对应的token信息，并重置缓存时间
        if(StringUtils.isNotEmpty(CookieUtils.getCookieValue(request,"userId"))){
            token = CookieUtils.getCookieValue(request,"token");
            String userRoleListStr= CookieUtils.getCookieValue(request,"userId");
            userRoleList = JSON.parseObject(userRoleListStr ,UserRoleList.class);
            //重置cookie时间
            CookieUtils.restCookieTime(request,cookiekeys);
            //重置Redis时间
            //插入Redis登录信息
            setSignInRedis(userId,ImmutableMap.of(RedisKeysEnum.USER_INFO.getKey(),userRoleListStr,RedisKeysEnum.USER_TOKEN.getKey(),token));
        }else {
           token = getLoginInfo(request,response,userInfo, userRoleList,token);
        }
        //插入登录信息
        actionService.save(actionVo);
        return userRoleList;
    }

    /**
     * 插入登录缓存信息
     * @param userId
     * @param map
     */
    public Boolean setSignInRedis(String userId, Map<String,String> map){
        Boolean b = true;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            RedisKeysEnum redisKeysEnum = RedisKeysEnum.getKeyByName(k);
            Boolean res = redisUtils.set(userId + "_" + redisKeysEnum.getKey(), v, RedisUtils.loginTime);
            if (!res) {
                log.error("setSignInRedis insert sign redis data Error ：" + k);
                b = false;
            }
        }
        RedisKeysEnum.getKeys(true);
        return b;
    }

    /**
     * 获取用户登录后的权限角色信息
     * @param userInfo
     * @param userRoleList
     */
    @Override
    public String getLoginInfo(HttpServletRequest request,HttpServletResponse response,MUser userInfo, UserRoleList userRoleList,String token) {
        String userId = userInfo.getUserId();
        //获取用户角色信息
        List<MUserRole> userRoles = userRoleService.getUserRolesByUserId(userInfo.getUserId());
        //获取用户权限信息
        if(CollectionUtils.isNotEmpty(userRoles)){
            //判断是否已登录过，已登录的直接返回对应的token信息，并重置缓存时间

            List<String> roles = new ArrayList<>();
            List<String> finalRoles = roles;
            userRoles.forEach(u->{
                if(StringUtils.isNotEmpty(u.getRoleIds())){
                    finalRoles.addAll(Arrays.asList(u.getRoleIds().split(",")));
                }
            });
            roles = roles.stream().distinct().collect(Collectors.toList());
            if(roles.size() > 0){
                //获取菜单信息
                List<MMenu> menuList = menuService.getMenusByRoleIds(roles);
                userRoleList.setMenuList(menuList);
                List<MRole> roleList = roleService.getRoleListByUserRole(roles);
                userRoleList.setRoleList(roleList);
            }
            //token已存在的不更新，只更新对应的缓存信息及cookie信息
            token = StringUtils.isEmpty(token) ? TokenUtils.getTokenByUserId(userId) : token;
            String userRoleListStr = JSON.toJSONString(userRoleList);
            //插入cookie
            CookieUtils.setCookie(request,response, ImmutableMap.of("userId",userId,"token",token,"userName",userInfo.getUserName(),"userRoleList", userRoleListStr));
            //插入Redis登录信息
            setSignInRedis(userId,ImmutableMap.of(RedisKeysEnum.USER_INFO.getKey(),userRoleListStr,RedisKeysEnum.USER_TOKEN.getKey(),token));
        }else{
            token = TokenUtils.getTokenByUserId(userId);
        }
        //公司信息
        PCompany company = companyService.getById(userInfo.getCompanyCode());
        if(company != null){
            userRoleList.setCompany(company);
        }
        response.setHeader("checkCookies", "1");
        return token;
    }

    /**
     * 退出登录
     * @param user 用户信息
     */
    @Override
    public void signOut(MUser user) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //账号解码
        String userId = BasicUtil.encodedBase64(user.getUserId());
        MUser userInfo = baseMapper.getUserByUserId(userId);
        if(userInfo == null){
            throw new WebResponseException(ResponseEnum.USER_ERROR);
        }
        //删除cookie
        CookieUtils.delCookie(request,cookiekeys );
        //删除用户Redis缓存信息
        redisUtils.del(RedisKeysEnum.getKeys(true));
        return ;
    }
    @Override
    public MUser getUserByUserId(String userId){
        return baseMapper.getUserByUserId(userId);
    }
}

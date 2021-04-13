package com.fairyBeauty.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * cookie帮助类
 *
 * @Author chenjianyun
 * @Date 2021/3/29 16:13
 * @Description cookie帮助类
 */
@Component
@Slf4j
public class CookieUtils {
    @Resource
    private RedisUtils redisUtils;
    @Value("${domain}")
    private void setDomain(String value){
        domain = value;
    }
    private static String domain; // 从配置文件 application.yml 中读取 domain 域
    /**
     * 需要更新的cookie对象
     */
    public static final List<String> cookiekeys = Arrays.asList("userId","token","userName","userRoleList");

    /**
     * 发送 cookie 到用户端
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @param cookieName cookie名
     * @param cookieValue cookie 要保存的值
     * @param cookieMaxAge cookie 的存活时间
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, Integer cookieMaxAge){
        setCookie(request,response,cookieName,cookieValue,cookieMaxAge,domain,null,null);
    }
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, Map<String,String> map){
        if(map != null){
            map.forEach((cookieName,cookieValue)->{
                setCookie(request,response,cookieName,cookieValue,RedisUtils.loginTime,domain,null,null);
            });
        }
    }
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, Map<String,String> map, Integer cookieMaxAge){
        if(map != null){
            map.forEach((cookieName,cookieValue)->{
                setCookie(request,response,cookieName,cookieValue,cookieMaxAge,domain,null,null);
            });
        }
    }

    private static final void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, Integer cookieMaxAge,String domain, String encodeString, Boolean httpOnly) {
        try {
            if(StringUtils.isBlank(encodeString)) {
                encodeString = "utf-8";
            }

            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxAge != null && cookieMaxAge > 0){
                cookie.setMaxAge(cookieMaxAge);
            }
            if (null != request) {// 设置域名的cookie
                cookie.setDomain(domain);
            }
            cookie.setPath("/");

            if(httpOnly != null) {
                cookie.setHttpOnly(httpOnly);
            }
            // 发送 cookie 到客户端
            response.addCookie(cookie);
        } catch (Exception e) {
            log.error("Cookie Encode Error.", e);
        }
    }

    /**
     * 得到Cookie的值, 不编码
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        return getCookieValue(request, cookieName, false);
    }

    /**
     * 得到Cookie的值,并返回
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName, boolean isDecoder) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null){
            return null;
        }
        String retValue = null;
        try {
            Cookie cookie = getCookie(request, cookieName);
            if(cookie == null){
                return null;
            }
            if (isDecoder) {
                retValue = URLDecoder.decode(cookie.getValue(), "UTF-8");
            } else {
                retValue = cookie.getValue();
            }
        } catch (UnsupportedEncodingException e) {
            log.error("获取Cookie信息失败", e);
        }
        return retValue;
    }

    /**
     * 得到Cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieName == null){
            return null;
        }
        Cookie retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieName)) {
                    retValue = cookieList[i];
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Cookie Decode Error.", e);
        }
        return retValue;
    }

    /**
     * 批量删除指定Cookie
     *
     * @param request
     * @param cookieNames 要删除的cookies
     * @return
     */
    public static void delCookie(HttpServletRequest request, List<String> cookieNames) {
        if(!CollectionUtils.isEmpty(cookieNames)){
            cookieNames.forEach(cookieName->{
                restCookieTime(request,cookieName,0);
            });
        }
    }

    /**
     * 刷新指定Cookie的时间
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static void restCookieTime(HttpServletRequest request, String cookieName, Integer time) {
        if(time == null){
            time =  RedisUtils.loginTime;
        }
        Cookie cookie = getCookie(request,cookieName);
        if (cookie == null){
            return;
        }
        //刷新指定Cookie的时间
        cookie.setMaxAge(time);
        return;
    }
    /**
     * 刷新指定Cookie的时间 默认3天
     *
     * @param request
     * @param cookieNames 要删除的cookies
     * @return
     */
    public static void restCookieTime(HttpServletRequest request, List<String> cookieNames) {
        restCookieTime(request,cookieNames,null);
    }
    /**
     * 刷新指定Cookie的时间
     *
     * @param request
     * @param cookieNames 要删除的cookies
     * @return
     */
    public static void restCookieTime(HttpServletRequest request, List<String> cookieNames,Integer time) {
        if(!CollectionUtils.isEmpty(cookieNames)){
            cookieNames.forEach(cookieName->{
                restCookieTime(request,cookieName,time);
            });
        }
    }
}
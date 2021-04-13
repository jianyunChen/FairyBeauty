package com.fairyBeauty.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fairyBeauty.entity.MUser;
import com.fairyBeauty.entity.vo.UserRoleList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
public interface MUserService extends IService<MUser> {
    /**
     * 登录
     * @param user 用户信息
     * @return
     */
    UserRoleList signIn(MUser user);

    /**
     * 获取用户登录信息并更新token
     * @param request
     * @param response
     * @param userInfo
     * @param userRoleList
     * @return
     */
    String getLoginInfo(HttpServletRequest request, HttpServletResponse response, MUser userInfo, UserRoleList userRoleList, String token);
    /**
     * 退出登录
     * @param user 用户信息
     * @return
     */
    void signOut(MUser user);

    /**
     * 根据用户编号获取用户信息
     * @param userId
     * @return
     */
    MUser getUserByUserId(String userId);
}

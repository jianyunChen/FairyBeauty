package com.fairyBeauty.service;

import com.fairyBeauty.entity.MUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
public interface MUserService extends IBaseService<MUser> {

    MUser getUserByUserIdAndPassword(String userId, String passWord);
}

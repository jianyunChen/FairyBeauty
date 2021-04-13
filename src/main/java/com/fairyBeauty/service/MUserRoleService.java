package com.fairyBeauty.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fairyBeauty.entity.MUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
public interface MUserRoleService extends IService<MUserRole> {

    List<MUserRole> getUserRolesByUserId(String userId);
}

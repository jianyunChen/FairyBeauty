package com.fairyBeauty.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fairyBeauty.entity.MRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
public interface MRoleService extends IService<MRole> {

    List<MRole> getRoleListByUserRole(List<String> userRoles);
}

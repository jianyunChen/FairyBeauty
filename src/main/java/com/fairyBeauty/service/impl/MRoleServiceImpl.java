package com.fairyBeauty.service.impl;

import com.fairyBeauty.entity.MRole;
import com.fairyBeauty.entity.MUserRole;
import com.fairyBeauty.mapper.MRoleMapper;
import com.fairyBeauty.service.MRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Service
public class MRoleServiceImpl extends ServiceImpl<MRoleMapper, MRole> implements MRoleService {

    @Override
    public List<MRole> getRoleListByUserRole(List<String> userRoles) {
        return baseMapper.getRoleListByUserRole(userRoles);

    }
}

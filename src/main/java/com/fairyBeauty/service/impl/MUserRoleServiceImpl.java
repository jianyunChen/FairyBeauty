package com.fairyBeauty.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fairyBeauty.entity.MUserRole;
import com.fairyBeauty.mapper.MUserRoleMapper;
import com.fairyBeauty.service.MUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Service
public class MUserRoleServiceImpl extends ServiceImpl<MUserRoleMapper, MUserRole> implements MUserRoleService {

    @Override
    public List<MUserRole> getUserRolesByUserId(String userId) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }
}

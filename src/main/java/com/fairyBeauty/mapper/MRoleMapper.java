package com.fairyBeauty.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fairyBeauty.entity.MRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Mapper
public interface MRoleMapper extends BaseMapper<MRole> {

    List<MRole> getRoleListByUserRole(List<String> userRoles);
}

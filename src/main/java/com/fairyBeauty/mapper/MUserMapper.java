package com.fairyBeauty.mapper;

import com.fairyBeauty.entity.MUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Resource
@Mapper
public interface MUserMapper extends BaseMapper<MUser> {

    MUser getUserByIdAndPassword(String userId, String passWord);

    MUser getUserByUserId(String userId);
}

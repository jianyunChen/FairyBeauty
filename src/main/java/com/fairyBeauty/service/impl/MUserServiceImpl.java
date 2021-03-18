package com.fairyBeauty.service.impl;

import com.fairyBeauty.entity.MUser;
import com.fairyBeauty.mapper.MUserMapper;
import com.fairyBeauty.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}

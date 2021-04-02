package com.fairyBeauty.mapper;

import com.fairyBeauty.entity.MCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fairyBeauty.service.impl.MCustomerScoreServiceImpl;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 客户信息表 Mapper 接口
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Mapper
public interface MCustomerMapper extends BaseMapper<MCustomer> {

}

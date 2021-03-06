package com.fairyBeauty.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fairyBeauty.entity.MMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Mapper
public interface MMenuMapper extends BaseMapper<MMenu> {
    
}

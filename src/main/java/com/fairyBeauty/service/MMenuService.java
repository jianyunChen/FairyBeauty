package com.fairyBeauty.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fairyBeauty.entity.MMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
public interface MMenuService extends IService<MMenu> {

    List<MMenu> getMenusByRoleIds(List<String> roles);
}

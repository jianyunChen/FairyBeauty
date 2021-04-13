package com.fairyBeauty.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fairyBeauty.entity.MMenu;
import com.fairyBeauty.entity.MRoleMenu;
import com.fairyBeauty.mapper.MMenuMapper;
import com.fairyBeauty.mapper.MRoleMenuMapper;
import com.fairyBeauty.service.MMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@Service
public class MMenuServiceImpl extends ServiceImpl<MMenuMapper, MMenu> implements MMenuService {

    @Resource
    private MRoleMenuMapper roleMenuMapper;
    @Override
    public List<MMenu> getMenusByRoleIds(List<String> roles) {
        List<MMenu> list = new ArrayList<>();
        //获取角色菜单
        List<MRoleMenu> roleMenus =  roleMenuMapper.selectBatchIds(roles);
        if(CollectionUtils.isNotEmpty(roleMenus)){
            List<String> menuIds = new ArrayList<>();
            for (MRoleMenu item : roleMenus) {
                menuIds.addAll(Arrays.asList(item.getMenuIds().split(",")));
            }
            list = baseMapper.selectBatchIds(menuIds.stream().distinct().collect(Collectors.toList()));
            list = list.stream().filter(u->u.getMenuIsUsed() == 1).collect(Collectors.toList());
            return list;
        }
        return list;
    }
}

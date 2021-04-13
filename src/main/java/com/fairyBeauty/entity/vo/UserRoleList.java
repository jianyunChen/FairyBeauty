package com.fairyBeauty.entity.vo;

import com.fairyBeauty.entity.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限列表
 *
 * @Author chenjianyun
 * @Date 2021/3/29 14:39
 * @Description 用户权限列表
 */
@Data
public class UserRoleList {
    public UserRoleList() {

    }

    public UserRoleList(MUser user, List<MRole> roleList, PCompany company) {
        setCompany(company);
        setRoleList(roleList);
        setUser(user);
    }

    public UserRoleList(MUser user, List<MRole> roleList) {
        setRoleList(roleList);
        setUser(user);
    }

    /**
     * 用户信息
     */
    private MUser user = new MUser();
    /**
     * 用户权限信息
     */
    private List<MRole> roleList = new ArrayList<>();
    /**
     * 用户菜单权限信息
     */
    private List<MMenu> menuList = new ArrayList<>();
    /**
     * 公司信息
     */
    private PCompany company = new PCompany();
}

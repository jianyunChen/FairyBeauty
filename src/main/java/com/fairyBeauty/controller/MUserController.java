package com.fairyBeauty.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fairyBeauty.entity.MUser;
import com.fairyBeauty.entity.base.PageVo;
import com.fairyBeauty.service.MUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/user")
public class MUserController extends BaseController<MUser, MUserService>{
    /**
     * 查询条件方法 可覆盖，默认无查询条件
     * @param list
     * @return
     */
    @Override
    public QueryWrapper getQueryWrapper(PageVo<MUser> list){
        return super.getQueryWrapper(list);
    };

    /**
     * 检测新增数据正确性
     * @param entity
     */
    @Override
    public void checkInsertData(MUser entity){

    }

    /**
     * 检测修改数据正确性
     * @param entity
     */
    @Override
    public void checkUpdateData(MUser entity){

    }
}
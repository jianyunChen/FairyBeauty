package com.fairyBeauty.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fairyBeauty.entity.MPosition;
import com.fairyBeauty.entity.base.PageVo;
import com.fairyBeauty.service.MPositionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 职位表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/position")
public class MPositionController extends BaseController<MPosition, MPositionService>{
    /**
     * 查询条件方法 可覆盖，默认无查询条件
     * @param list
     * @return
     */
    @Override
    public QueryWrapper getQueryWrapper(PageVo<MPosition> list){
        return super.getQueryWrapper(list);
    };

    /**
     * 检测新增数据正确性
     * @param entity
     */
    @Override
    public void checkInsertData(MPosition entity){

    }

    /**
     * 检测修改数据正确性
     * @param entity
     */
    @Override
    public void checkUpdateData(MPosition entity){

    }
}
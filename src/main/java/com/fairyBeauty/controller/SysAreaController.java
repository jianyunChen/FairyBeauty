package com.fairyBeauty.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fairyBeauty.entity.SysArea;
import com.fairyBeauty.entity.base.PageVo;
import com.fairyBeauty.service.SysAreaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域信息表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/sys_area")
public class SysAreaController extends BaseController<SysArea, SysAreaService>{
    /**
     * 查询条件方法 可覆盖，默认无查询条件
     * @param list
     * @return
     */
    @Override
    public QueryWrapper getQueryWrapper(PageVo<SysArea> list){
        return super.getQueryWrapper(list);
    };

    /**
     * 检测新增数据正确性
     * @param entity
     */
    @Override
    public void checkInsertData(SysArea entity){

    }

    /**
     * 检测修改数据正确性
     * @param entity
     */
    @Override
    public void checkUpdateData(SysArea entity){

    }
}
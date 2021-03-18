package com.fairyBeauty.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/nxgl/m-customer")
public class MCustomerController {

    @ApiOperation(value = "m-customer接口", notes = "m-customer")
    @ApiImplicitParam(value = "姓名", name = "name", required = true, dataType = "String")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String abc(@PathVariable String name) {
        return name;
    }
}

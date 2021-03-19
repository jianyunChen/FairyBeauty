package com.fairyBeauty.controller;


import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/customer")
public class MCustomerController {

    public MCustomerController(){
        System.out.println("MCustomerController");
    }
    @RequestMapping("/test")
    public String test(){
        return "测试";
    }


    @ApiOperation(value = "客户信息接口", notes = "customer")
    @ApiImplicitParam(value = "姓名", name = "name", required = true, dataType = "String")
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String name(@PathVariable String name) {
        return name;
    }
}

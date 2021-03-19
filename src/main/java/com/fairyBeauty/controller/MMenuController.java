package com.fairyBeauty.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/menu")
public class MMenuController {

    @GetMapping("test")
    public Object abc() {
        return "123";
    }
}

package com.fairyBeauty.controller;


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
public class MPositionController {

    @GetMapping("test")
    public Object abc() {
        return "123";
    }
}

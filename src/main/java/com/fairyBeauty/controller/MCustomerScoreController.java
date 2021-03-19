package com.fairyBeauty.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户评价表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/customer-score")
public class MCustomerScoreController {

    @GetMapping("test")
    public Object abc() {
        return "123";
    }
}

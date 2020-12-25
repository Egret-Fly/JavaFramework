package com.xd.eduOrder.controller;


import com.xd.eduOrder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/eduOrder/t-order")
@CrossOrigin
public class TOrderController {

    @Autowired
    private TOrderService orderService;

    //1 生成订单的方法
    @PostMapping("")
}


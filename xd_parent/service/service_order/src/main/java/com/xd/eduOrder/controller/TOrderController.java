package com.xd.eduOrder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.commonutils.JwtUtils;
import com.xd.commonutils.R;
import com.xd.eduOrder.client.EduClient;
import com.xd.eduOrder.client.UcenterClient;
import com.xd.eduOrder.domain.TOrder;
import com.xd.eduOrder.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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



    //1.生成订单方法
    @PostMapping("createOrder/{courseId}")
    public R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String token = JwtUtils.getMemberIdByJwtToken(request);
        //创建订单，返回订单号
        String orderNo = orderService.createOrders(courseId,token);
        return R.ok().data("orderId",orderNo);
    }

    //根据订单id查询订单信息
    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper<TOrder> tOrderQueryWrapper = new QueryWrapper<>();
        tOrderQueryWrapper.eq("order_no",orderId);
        TOrder order = orderService.getOne(tOrderQueryWrapper);
        return R.ok().data("item",order);
    }


}


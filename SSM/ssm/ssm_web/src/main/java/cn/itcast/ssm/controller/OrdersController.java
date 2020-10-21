package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService orderService;


    //查询全部产品
    @RequestMapping("/findAll.do")
    public ModelAndView finaAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll();
        for (Orders p : ordersList) {
            System.out.println(p);
        }
        mv.addObject("productList",ordersList);
        mv.setViewName("product-list2");
        return mv;

    }
}

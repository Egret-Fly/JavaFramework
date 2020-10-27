package cn.itcast.ssm.controller;

import cn.itcast.ssm.domain.Orders;
import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.domain.Traveller;
import cn.itcast.ssm.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.management.OperationsException;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService orderService;


    //查询全部订单--未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView finaAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> ordersList = orderService.findAll();
//        for (Orders p : ordersList) {
//            System.out.println(p);
//        }
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//
//    }

    //查询全部订单
    @RequestMapping("/findAll.do")
    public ModelAndView finaAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page,@RequestParam(name="size",required = true,defaultValue = "4") int size) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);

        //pageInfo就是一个分页bean
        PageInfo pageInfo =new  PageInfo(ordersList);
        mv.addObject("PageInfo",pageInfo);

        mv.setViewName("orders-page-list");
        return mv;

    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true) String ordersId) {
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");



        return mv;
    }


}

package cn.itcast.ssm.controller;


import cn.itcast.ssm.domain.Product;
import cn.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        System.out.println(product);
        System.out.println("进来了save.do");
        productService.save(product);
        System.out.println("hhhh");
        return "redirect:findAll.do";
    }

    //查询全部产品
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        for (Product p : ps) {
            System.out.println(p);
        }
        mv.addObject("productList",ps);
        mv.setViewName("product-list2");
        return mv;
    }
}

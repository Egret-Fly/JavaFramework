package cn.itcast.controller;

import cn.itcast.Service.Impl.ItemsServiceImpl;
import cn.itcast.domain.Items2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items/")
public class ItemsController {
    @Autowired
    private ItemsServiceImpl service;

    @RequestMapping("/findItems")
    public String findItems(Model model){
        Items2 byId = service.findById(1);
        model.addAttribute("item",byId);
        return "viewresult";

    }
}

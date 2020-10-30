package cn.itcast.ssm.controller;

import cn.itcast.ssm.dao.IPermissionDao;
import cn.itcast.ssm.domain.Permission;
import cn.itcast.ssm.service.IPermisssionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermisssionService permisssionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permisssions = permisssionService.findAll();
        mv.addObject("permisssionList",permisssions);
        mv.setViewName("permission-list");

        return mv;

    }

    @RequestMapping("/save.do")
    public String save(Permission permission){
        permisssionService.save(permission);
        return "redirect:findAll.do";
    }
}

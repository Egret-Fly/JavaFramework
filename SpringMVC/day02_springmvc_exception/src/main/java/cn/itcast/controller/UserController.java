package cn.cn.itcast.controller.itcast.controller;


import cn.cn.itcast.controller.itcast.exception.SysException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/testException")
    public  String testException() throws SysException{
        System.out.println("testException执行了");
        //模拟异常
        try {
            int a = 10/0;
        } catch (Exception e) {
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("查询所有用户出现错误");
        }
        return "success";

    }
}

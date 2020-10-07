package cn.cn.itcast.controller.itcast.controller;

import cn.cn.itcast.controller.itcast.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法执行了...");
        //模拟从数据库查询出user对象
        User user = new User();
        user.setUsername("美美");
        user.setAge(18);
        user.setPassword("710103");
        //model对象
        model.addAttribute("user",user);
        return "success";
    }


    /**
     * 是void
     * 请求转发一次请求，不用编写项目的名称
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid方法执行了...");
        //编写请求转发的程序
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);

        //重定向
        //response.sendRedirect(request.getContextPath()+"/index.jsp");
        //System.out.println(request.getContextPath());

        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //直接响应
        response.getWriter().print("你好");

    }


    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        System.out.println("estModelAndView方法执行了...");
        //模拟从数据库查询出user对象
        User user = new User();
        user.setUsername("美美");
        user.setAge(18);
        user.setPassword("710103");

        //把user对象存储到mv对象中,也会把user对象存入到request对象
        mv.addObject("user",user);

        //跳转到哪个页面
        mv.setViewName("success");
        return mv;
    }


    /**
     * 使用关键字方式进行转发或者重定向
     * @return
     */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect方法执行了...");
        //请求的转发
        //return "forward:/WEB-INF/pages/success.jsp";

        //重定向
        return "redirect:/index.jsp";
    }

    /**
     * 模拟异步响应请求
     * @return
     */
    @RequestMapping("/testAjax")
    public void testAjax(@RequestBody String body){
        System.out.println("testAjax执行了...");
        System.out.println(body);

    }
}

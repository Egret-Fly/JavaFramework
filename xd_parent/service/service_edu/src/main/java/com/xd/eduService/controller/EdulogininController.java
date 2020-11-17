package com.xd.eduService.controller;


import com.xd.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduService/user")
@CrossOrigin //解决跨域
public class EdulogininController {

    //login
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605193550393&di=84643fa9c0b852766d93d83cd0542d1d&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F30%2F29%2F01300000201438121627296084016.jpg");
    }


}

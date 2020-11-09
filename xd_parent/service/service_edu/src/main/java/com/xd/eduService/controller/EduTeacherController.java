package com.xd.eduService.controller;


import com.xd.eduService.domain.EduTeacher;
import com.xd.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/eduService/edu-teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //1 查询所有数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        //调用service的方法实现查询的所有操作
        List<EduTeacher> list = teacherService.list(null);
        return list;

    }

    //2 逻辑删除讲师的方法
    @DeleteMapping("{id}")
    public  boolean removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        return flag;
    }


}


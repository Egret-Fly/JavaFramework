package com.xd.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.commonutils.R;
import com.xd.eduService.domain.EduCourse;
import com.xd.eduService.domain.EduTeacher;
import com.xd.eduService.service.EduCourseService;
import com.xd.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //查询前8名课程,查询前4名师
    @GetMapping("index")
    public R index(){
        //查询前8条课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last(" limit 8 ");
        List<EduCourse> list = eduCourseService.list(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapper2 = new QueryWrapper<>();
        wrapper2.orderByDesc("id");
        wrapper2.last(" limit 4 ");
        List<EduTeacher> list2 = teacherService.list(wrapper2);


        return  R.ok().data("eduList",list).data("teacherList",list2);


    }

}

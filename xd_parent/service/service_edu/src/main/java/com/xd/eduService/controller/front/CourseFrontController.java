package com.xd.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.commonutils.R;
import com.xd.eduService.domain.EduCourse;
import com.xd.eduService.domain.EduTeacher;
import com.xd.eduService.service.EduCourseService;
import com.xd.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/teacherfront")
@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;


    @GetMapping("getTeacherFront/{page}/{limit}")
    public R getTeacherFront(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = eduTeacherService.getTeaccherFrontList(pageTeacher);
        return R.ok().data(map);
    }

    //2.讲师详情
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherInfo(@PathVariable String teacherId){
        //根据讲师id查询讲师基本信息
        EduTeacher eduteacher = eduTeacherService.getById(teacherId);

        //2根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> eduCourseList = eduCourseService.list(wrapper);
        return R.ok().data("teacher",eduteacher).data("courseList",eduCourseList);
    }
}

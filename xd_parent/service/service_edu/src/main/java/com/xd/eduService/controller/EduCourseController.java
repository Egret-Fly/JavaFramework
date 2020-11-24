package com.xd.eduService.controller;


import com.xd.commonutils.R;
import com.xd.eduService.domain.vo.CourseInfoVo;
import com.xd.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/eduService/edu-course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    @PostMapping("saveCourse")
    public R saveCourse(@RequestBody CourseInfoVo courseInfoVo){
        String cid = eduCourseService.saveCourse(courseInfoVo);
        return R.ok().data("courseId",cid);
    }

}


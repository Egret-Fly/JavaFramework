package com.xd.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.commonutils.R;
import com.xd.eduService.domain.EduCourse;
import com.xd.eduService.domain.EduTeacher;
import com.xd.eduService.domain.vo.CourseInfoVo;
import com.xd.eduService.domain.vo.CoursePublishVo;
import com.xd.eduService.domain.vo.CourseQuery;
import com.xd.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R getCourseList(@PathVariable long current, @PathVariable long limit,
                           @RequestBody(required = false) CourseQuery courseQuery) {

        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        String name = courseQuery.getName();
        String status = courseQuery.getStatus();
        if (!StringUtils.isEmpty(name)){
            eduCourseQueryWrapper.like("title",name);
        }
        if (!StringUtils.isEmpty(status)){
            eduCourseQueryWrapper.like("status",status);
        }
        List<EduCourse> l = eduCourseService.list(null);
        eduCourseService.page(pageCourse,eduCourseQueryWrapper);
        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords();//数据list集合

        Map map = new HashMap();
        map.put("total",total);
        map.put("records",records);
        return R.ok().data(map);
    }
    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishcourseInfoVo",coursePublishVo);
    }

    //课程最终发布
    //修改课程状态
    @GetMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    //删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }

}


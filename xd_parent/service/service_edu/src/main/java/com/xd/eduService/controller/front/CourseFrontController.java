package com.xd.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.commonutils.R;
import com.xd.commonutils.ordervo.CourseWebVoOrder;
import com.xd.eduService.domain.EduCourse;
import com.xd.eduService.domain.EduTeacher;
import com.xd.eduService.domain.chapter.chapterVo;
import com.xd.eduService.domain.frontvo.CourseFrontVo;
import com.xd.eduService.domain.frontvo.CourseWebVo;
import com.xd.eduService.service.EduChapterService;
import com.xd.eduService.service.EduCourseService;
import com.xd.eduService.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/Coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    //条件查询带分页
    @PostMapping("getCourseFront/{page}/{limit}")
    public R geCourseFront(@PathVariable long page, @PathVariable long limit,
                           @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = eduCourseService.getCourseFrontList(pageCourse,courseFrontVo);
        return R.ok().data(map);
    }

    //2 课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节小节
        List<chapterVo> chapterById = eduChapterService.getChapterById(courseId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterById);

    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo baseCourseInfo = eduCourseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo,courseWebVoOrder);
        return courseWebVoOrder;


    }


}

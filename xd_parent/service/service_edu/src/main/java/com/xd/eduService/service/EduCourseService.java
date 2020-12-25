package com.xd.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xd.eduService.domain.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.eduService.domain.frontvo.CourseFrontVo;
import com.xd.eduService.domain.frontvo.CourseWebVo;
import com.xd.eduService.domain.vo.CourseInfoVo;
import com.xd.eduService.domain.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCouse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}

package com.xd.eduService.service;

import com.xd.eduService.domain.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.eduService.domain.vo.CourseInfoVo;

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
}

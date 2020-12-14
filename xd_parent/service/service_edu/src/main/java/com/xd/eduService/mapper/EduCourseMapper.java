package com.xd.eduService.mapper;

import com.xd.eduService.domain.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.eduService.domain.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublishVo getPublishCourseInfo(String courseId);
}

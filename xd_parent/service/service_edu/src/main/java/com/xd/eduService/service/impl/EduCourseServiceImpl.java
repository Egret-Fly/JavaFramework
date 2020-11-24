package com.xd.eduService.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xd.eduService.domain.EduCourse;
import com.xd.eduService.domain.EduCourseDescription;
import com.xd.eduService.domain.vo.CourseInfoVo;
import com.xd.eduService.mapper.EduCourseMapper;
import com.xd.eduService.service.EduCourseDescriptionService;
import com.xd.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.servicebase.exceptionhandler.xdException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;


    @Override
    public String saveCourse(CourseInfoVo courseInfoVo) {
        //插入课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            throw new xdException(20001,"插入失败");
        }

        String cid = eduCourse.getId();
        //插入课程描述表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo,eduCourseDescription);
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;

    }
}

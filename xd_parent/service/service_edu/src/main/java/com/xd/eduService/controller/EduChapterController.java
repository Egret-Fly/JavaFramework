package com.xd.eduService.controller;


import com.xd.commonutils.R;
import com.xd.eduService.domain.chapter.chapterVo;
import com.xd.eduService.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/eduService/chapter")
public class EduChapterController {

    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("GetChpater/{courseId}")
    public R getChapterById(@PathVariable String courseId){
        List<chapterVo> chapeter = eduChapterService.getChapterById(courseId);
        return R.ok().data("items",chapeter);
    }


}


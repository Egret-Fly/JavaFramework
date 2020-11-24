package com.xd.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.eduService.domain.EduChapter;
import com.xd.eduService.domain.EduVideo;
import com.xd.eduService.domain.chapter.VideoVo;
import com.xd.eduService.domain.chapter.chapterVo;
import com.xd.eduService.mapper.EduChapterMapper;
import com.xd.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public List<chapterVo> getChapterById(String courserId) {
        //根据id查询一级分类chapter
        QueryWrapper<EduChapter> eduChapterRes = new QueryWrapper<>();
        eduChapterRes.eq("course_id",courserId);
        List<EduChapter> eduChapters = baseMapper.selectList(eduChapterRes);
        //根据id查询二级分类video
        List<chapterVo> chapterVos = new ArrayList<>();
        for (int i = 0; i < eduChapters.size(); i++) {
            chapterVo chapterVo = new chapterVo();
            EduChapter eduChapter= eduChapters.get(i);
            BeanUtils.copyProperties(eduChapter,chapterVo);

            QueryWrapper<EduVideo> eduVideos = new QueryWrapper<>();
            eduVideos.eq("course_id",courserId);
            eduVideos.eq("chapter_id",eduChapters.get(i).getId());
            List<EduVideo> EduVideos = eduVideoService.list(eduVideos);
            List<VideoVo> videoVos = new ArrayList<>();
            for (int i1 = 0; i1 < EduVideos.size(); i1++) {
                VideoVo videoVo = new VideoVo();
                EduVideo ev = EduVideos.get(i1);
                BeanUtils.copyProperties(ev,videoVo);
                videoVos.add(videoVo);
            }
            chapterVo.setChildren(videoVos);

            chapterVos.add(chapterVo);
        }




        return chapterVos;


    }
}

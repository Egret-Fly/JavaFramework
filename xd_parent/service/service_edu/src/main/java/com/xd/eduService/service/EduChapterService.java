package com.xd.eduService.service;

import com.xd.eduService.domain.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.eduService.domain.chapter.chapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-11-19
 */
public interface EduChapterService extends IService<EduChapter> {


    List<chapterVo> getChapterById(String courserId);

    void removeChapterByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
}

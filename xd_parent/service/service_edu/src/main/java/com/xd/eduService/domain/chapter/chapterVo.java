package com.xd.eduService.domain.chapter;

import com.xd.eduService.domain.subject.TwoSubject;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class chapterVo {

    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<VideoVo> children = new ArrayList<>();
}

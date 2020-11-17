package com.xd.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.eduService.domain.EduSubject;
import com.xd.eduService.domain.entity.SubjectData;
import com.xd.eduService.service.EduSubjectService;
import com.xd.servicebase.exceptionhandler.xdException;
import javax.security.auth.Subject;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    public SubjectExcelListener() {
    }

    //读取Excel内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new xdException(200001,"文件数据为空");
        }
        //一行一行读取,每次两个值，第一个一级分类，第二个二级分类
        EduSubject existeduSubject = this.existOneSubject(subjectService,subjectData.getOneSubjectName());
        if (existeduSubject==null){//没有相同一级分类，进行添加
            existeduSubject = new EduSubject();
            existeduSubject.setParentId("0");
            existeduSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existeduSubject);
        }

        //获取一级分类id值
        String pid = existeduSubject.getId();
        EduSubject existtwoeduSubject = this.existTwoSubject(subjectService,subjectData.getTwoSubjectName(),pid);
        if (existtwoeduSubject==null){//没有相同二级分类，进行添加
            existtwoeduSubject = new EduSubject();
            existtwoeduSubject.setParentId(pid);
            existtwoeduSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existtwoeduSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject onesubject= subjectService.getOne(wrapper);
        return onesubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twosubject= subjectService.getOne(wrapper);
        return twosubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {


    }
}

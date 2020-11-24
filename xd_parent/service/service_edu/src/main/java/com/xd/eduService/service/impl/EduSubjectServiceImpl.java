package com.xd.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.eduService.domain.EduSubject;
import com.xd.eduService.domain.entity.SubjectData;
import com.xd.eduService.domain.subject.OneSubject;
import com.xd.eduService.domain.subject.TwoSubject;
import com.xd.eduService.listener.SubjectExcelListener;
import com.xd.eduService.mapper.EduSubjectMapper;
import com.xd.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-11-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream in  = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {

        //查询所有一级分类 parentid = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubject = baseMapper.selectList(wrapperOne);

        //查询所有二级分类 parentid = 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubject = baseMapper.selectList(wrapperTwo);

        List<OneSubject> finalSubject= new ArrayList<>();

        for (int i = 0; i < oneSubject.size(); i++) {

            OneSubject oSubject = new OneSubject();
            BeanUtils.copyProperties(oneSubject.get(i),oSubject);


            List<TwoSubject> twoSubject2= new ArrayList<>();
            for (int m =0 ;m< twoSubject.size();m++){

                if (twoSubject.get(m).getParentId().equals(oSubject.getId())){
                    TwoSubject tSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubject.get(m),tSubject);
                    twoSubject2.add(tSubject);
                }


            }
            oSubject.setChildren(twoSubject2);
            finalSubject.add(oSubject);
        }

        return finalSubject;
    }
}

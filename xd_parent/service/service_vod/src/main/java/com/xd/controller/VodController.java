package com.xd.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xd.commonutils.R;
import com.xd.service.VodService;
import com.xd.servicebase.exceptionhandler.xdException;
import com.xd.utils.ConstantVodUtils;
import com.xd.utils.InitVodClient;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;


    //上传视频到阿里云
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId",videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request =  new DeleteVideoRequest();
            //向Request设置视频id
            request.setVideoIds(id);
            //调用初始化对象方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new xdException(20001,"删除失败");
        }

    }

    @DeleteMapping("delete-batch")
    public R deletebatchVideo(@RequestParam("videoList") List<String> videoList){
        vodService.deleteVideoBatch(videoList);
        return R.ok();
    }
}

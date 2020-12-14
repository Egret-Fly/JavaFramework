package com.xd.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xd.commonutils.R;
import com.xd.service.VodService;
import com.xd.servicebase.exceptionhandler.xdException;
import com.xd.utils.ConstantVodUtils;
import com.xd.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {


        String FileName = file.getOriginalFilename();
        String title= FileName.substring(0, FileName.lastIndexOf("."));
        InputStream inputStream  = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET, title,FileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        String videoId =null;
        if (response.isSuccess()) {
            videoId= response.getVideoId() ;
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                   }
        return videoId;
    }

    @Override
    public void deleteVideoBatch(List videoList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID,ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request =  new DeleteVideoRequest();
            //向Request设置视频id
            String ids = StringUtils.join(videoList.toArray(), ",");
            request.setVideoIds(ids);
            //调用初始化对象方法实现删除
            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
            throw new xdException(20001,"删除失败");
        }
    }
}

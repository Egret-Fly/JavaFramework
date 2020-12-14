package com.xd.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

public class TestVod {

    public static void main(String[] args) throws ClientException {
        UploadVideoRequest request = new UploadVideoRequest("LTAI4Fz9jyo8Xkjhw1dMXX5b", "xVeVfEJZYH21P1mC73Spq9WDoJ5WK3", "进制转换", "G:\\BaiduNetdiskDownload\\2020尚硅谷 在线教育 spring boot+springcloud分布式项目\\资料\\项目资料\\1-阿里云上传测试视频\\1_1_1_03_计算机存储单元.flv");
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }




    }
    public static void getPlayAuth()  throws ClientException{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient();
        //创建视频地址的resquest和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();

        request.setVideoId("1ec7500973bd4d91b833026f518b92d8");
        //调用初始化对象方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth: "+response.getPlayAuth());
    }

    public static void getPlayUrl()  throws ClientException{
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient();

        //创建视频地址的resquest和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("1ec7500973bd4d91b833026f518b92d8");
        //向request设置视频id
        response = client.getAcsResponse(request);


        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
    }
}

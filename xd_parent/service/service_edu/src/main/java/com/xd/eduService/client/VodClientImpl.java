package com.xd.eduService.client;

import com.xd.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VodClientImpl implements  VodClient{
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deletebatchVideo(List<String> videoList) {
        return R.error().message("删除多个视频出错了");
    }
}

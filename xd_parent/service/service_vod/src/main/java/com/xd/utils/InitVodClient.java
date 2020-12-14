package com.xd.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

public class InitVodClient {

    public static DefaultAcsClient initVodClient(String keyId,String keySecrt) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId,keyId,keySecrt);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}

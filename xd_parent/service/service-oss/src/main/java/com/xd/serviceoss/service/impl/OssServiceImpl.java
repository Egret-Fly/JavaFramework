package com.xd.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xd.serviceoss.service.OssService;
import com.xd.serviceoss.utils.ConstantPropertiesUtils;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.END_POINT;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret =  ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

// 创建OSSClient实例。
        try {
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    // 上传文件流。
            InputStream inputStream = file.getInputStream();

            String filename  = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            filename = uuid+filename;

            //2 把文件按日期进行分类
            String s = new DateTime().toString("yyyy/MM/dd");

            filename = s +"/"+ filename;

            //调用oss方法实现上传
                // 第一个参数 Bucket名称
                //第二个参数 上传到oss文件名称和路径
            ossClient.putObject(bucketName, filename, inputStream);

    // 关闭OSSClient。
            ossClient.shutdown();

            //返回路径
            //需要把上传阿里云的路径手动拼接出来
            String url = "https://"+bucketName+"."+endpoint+"/"+filename;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

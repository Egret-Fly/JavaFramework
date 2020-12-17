package com.xd.eduCenter.controller;

import com.google.gson.Gson;
import com.xd.commonutils.JwtUtils;
import com.xd.eduCenter.domain.UcenterMember;
import com.xd.eduCenter.service.UcenterMemberService;
import com.xd.eduCenter.utils.ConstantWxUtils;
import com.xd.eduCenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@CrossOrigin
//@RestController //只是请求地址，并不需要返回，需要controller
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {


    @Autowired
    private UcenterMemberService memberService;

    //2 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code,String state) throws Exception {
        //1 获取code值，临时票据，类似于验证码
        System.out.println("code:"+code);

        //2 拿着code请求微信固定的地址，得到两个值access_token 和 open_id
        //向认证服务器发送请求换取access_token
        String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s" +
                "&secret=%s" +
                "&code=%s" +
                "&grant_type=authorization_code";
        // 拼接三个参数: id 秘钥 和code值
        String accessTokenUrl = String.format(baseAccessTokenUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                ConstantWxUtils.WX_OPEN_APP_SECRET,
                code
                );
        //请求这个拼接好的地址，得到返回两个值
        //使用httpclient发送请求,得到返回结果
        String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
        //从accessTokenInfo获取access_token和openid
        //把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
        //使用json转换工具Gson
        Gson gson = new Gson();
        HashMap hashMap = gson.fromJson(accessTokenInfo, HashMap.class);
        String access_token = (String) hashMap.get("access_token");
        String openid = (String)hashMap.get("openid");

        //把扫描人信息加入数据库
        UcenterMember member = memberService.getOnpenIdMember(openid);

        if (member == null){
            //访问微信的资源服务器，获取用户信息
            String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";
            String userInfoUrl = String.format(baseUserInfoUrl, access_token, openid);
            String userInfo = HttpClientUtils.get(userInfoUrl);
            System.out.println(userInfo);
            HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
            String nickname = (String) userInfoMap.get("nickname");//昵称
            String headimgurl = (String) userInfoMap.get("headimgurl");//头像

            member = new UcenterMember();
            member.setOpenid(openid);
            member.setAvatar(headimgurl);
            member.setNickname(nickname);
            memberService.save(member);
        }

        //使用jwt根据member对象生成token字符串
        String jwtToken = JwtUtils.getJwtToken(member.getId(),member.getNickname());
        //3 拿到access_token和openid获取用户名和密码
        return "redirect:http://localhost:3000?token="+jwtToken;
    }

    //1 生成微信扫描二维码
    @GetMapping("login")
    public String getWxcode(){
        //固定地址，后面拼接参数
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "xd"
                );
        //请求微信地址

        return "redirect:"+qrcodeUrl;
    }
}

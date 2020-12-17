package com.xd.eduCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xd.commonutils.JwtUtils;
import com.xd.commonutils.MD5;
import com.xd.eduCenter.domain.UcenterMember;
import com.xd.eduCenter.domain.vo.RegisterVo;
import com.xd.eduCenter.mapper.UcenterMemberMapper;
import com.xd.eduCenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xd.servicebase.exceptionhandler.xdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-12-10
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //登录的方法
    @Override
    public String loginin(UcenterMember member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw  new xdException(20001,"登陆失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember ==null){
            //没有这个手机号
            throw  new xdException(20001,"登陆失败，账号不存在");
        }
        //判断密码
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw  new xdException(20001,"登陆失败,密码错误");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDisabled()){
            throw  new xdException(20001,"登陆失败,账号以被封禁");

        }
        //登录成功
        return JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());



    }

    //注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        if (StringUtils.isEmpty(code)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            throw new xdException(20001,"缺少必要信息，注册失败");
        }

        String rediscode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(rediscode)){
            throw new xdException(20001,"验证码错误，注册失败");
        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0){
            throw new xdException(20001,"手机号已存在，注册失败");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJI53RcCuc1no02os6ZrattWGiazlPnicoZQ59zkS7phNdLEWUPDk8fzoxibAnXV1Sbx0trqXEsGhXPw/132");
        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getOnpenIdMember(String openid) {
       QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
       wrapper.eq("openid",openid);
       return  baseMapper.selectOne(wrapper);
    }
}

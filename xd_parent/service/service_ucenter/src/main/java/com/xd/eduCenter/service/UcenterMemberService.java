package com.xd.eduCenter.service;

import com.xd.eduCenter.domain.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xd.eduCenter.domain.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-12-10
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String loginin(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOnpenIdMember(String openid);
}

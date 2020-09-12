package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.Userdao;
import cn.itcast.travel.dao.impl.UserdaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private Userdao ud = new UserdaoImpl();
    /**
     * 注册用户
     * */
    @Override
    public boolean regist(User u) {
        User us = ud.findByUsername(u.getUsername());
        //判断us是否为null
        if(us != null){
            return false;
        }
        //2.保存用户信息
        u.setCode(UuidUtil.getUuid());
        u.setStatus("N");
        ud.save(u);

        //3.激活邮件发送
        String content = "<a href='http://localhost:8030/travel/user/active?code="+u.getCode()+"'>点击激活</a>";
        MailUtils.sendMail(u.getEmail(),content,"激活邮件");
        return true;

    }

    @Override
    public boolean active(String code) {
        User user = ud.findByCode(code);
        if(user!=null){
            ud.updataStatus(user);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User login(User u) {
        User byUsernameAndPassword = ud.findByUsernameAndPassword(u.getUsername(),u.getPassword());
        return byUsernameAndPassword;

    }
}

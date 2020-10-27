package cn.itcast.ssm.service.Impl;

import cn.itcast.ssm.dao.IUserDao;
import cn.itcast.ssm.domain.UserInfo;
import cn.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己 的用户对象封装成Details
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority());
        return user;
    }
    //作用就是返回一个List集合，集合中装入的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> list= new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }
}

package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * */
    public boolean regist(User u);
    public boolean active(String code);
    public User login(User u);
}

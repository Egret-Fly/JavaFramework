package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.User;

import java.util.List;

public interface Userdao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    /**
     *根据激活码查询用户对象
     */
    public User findByCode(String code);

    public void updataStatus(User u);

    public User findByUsernameAndPassword(String username,String password);


}

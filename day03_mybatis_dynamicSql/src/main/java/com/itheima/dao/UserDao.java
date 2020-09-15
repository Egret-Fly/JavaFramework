package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);


    /**
     * 根据id查询用户信息
     */
    User findById(Integer userId);
    /**
     * 根据名称模糊查询
     */
    List<User> findByName(String username);

    /**
     * 根据传入参数条件
     * @param user 查询的条件：有可能有用户名，有可能有性别，也有可能有地址，还有可能都有。
     * @return
     */
    List<User> findUserByCondititon(User user);




}

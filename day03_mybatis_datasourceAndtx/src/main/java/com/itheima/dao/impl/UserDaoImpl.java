package com.itheima.dao.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        List<User> users = session.selectList("com.itheima.dao.UserDao.findAll");//参数就是获取配置信息的Key
        session.close();
        return users;
    }

    public void saveUser(User user) {

        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        session.insert("com.itheima.dao.UserDao.saveUser",user);//参数就是获取配置信息的Key
        session.commit();
        session.close();
    }

    public void updateUser(User user) {

        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        session.update("com.itheima.dao.UserDao.updateUser",user);//参数就是获取配置信息的Key
        session.commit();
        session.close();

    }

    public void deleteUser(Integer userId) {

    }

    public User findById(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法，实现查询列表
        User users = session.selectOne("com.itheima.dao.UserDao.findById",userId);//参数就是获取配置信息的Key
        session.close();
        return users;
    }

    public List<User> findByName(String username) {
        return null;
    }

    public int findTotal() {
        return 0;
    }
}

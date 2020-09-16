package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class AnnotationCurd {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userdao;

    @Before
    public  void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userdao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("annotation");
        user.setAddress("北京市昌平区");

        userdao.saveUser(user);

    }


    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(53);
        user.setUsername("mybatis annotation");
        user.setAddress("西安市长安");
        user.setBirthday(new Date());

        userdao.updateUser(user);

    }

    @Test
    public void testDelete(){


        userdao.deleteUser(50);

    }

    @Test
    public void testfindONe(){


        User user = userdao.findById(52);
        System.out.println(user);
    }
}

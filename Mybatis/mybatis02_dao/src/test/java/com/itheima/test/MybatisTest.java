package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;

import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的curd操作
 */
public class MybatisTest {

    private InputStream in;

    private UserDao userdao;
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory对象

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.使用工厂对象，创建dao对象

        userdao = new UserDaoImpl(factory);
    }
    @After
    public void destory() throws IOException {

        in.close();
    }



    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {
        List<User> users = userdao.findAll();

        for (User user : users) {
            System.out.println(user);
        }

    }


    /**
     * 测试保存操作
     */
    @Test
    public  void testSave() throws IOException {
        User user = new User();
        user.setUsername("mybatis 04");
        user.setAddress("西安市长安区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前"+user);
        //1保存
        userdao.saveUser(user);
        System.out.println("保存之后："+user);



    }


    /**
     * 更新操作
     * @throws IOException
     */
    @Test
    public  void testUpdate() throws IOException {
        User user = new User();
        user.setId(50);
        user.setUsername("mybatis updateuser2");
        user.setAddress("西安市碑林区");
        user.setSex("男");
        user.setBirthday(new Date());
        //1保存
        userdao.updateUser(user);



    }

    /**
     * 删除
     * @throws IOException
     */
    @Test
    public  void testDelete() throws IOException {

        userdao.deleteUser(49);


    }


    /**
     * 测试查询所有
     */
    @Test
    public void testFindById() throws IOException {
        User users = userdao.findById(48);

        System.out.println(users);
    }

    /**
     * 模糊查询
     */
    @Test
    public void testFindByName() throws IOException {
        String name = "王";
        List<User> users = userdao.findByName("%"+name+"%");

        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 测试查询所有记录条数
     */
    @Test
    public void testFindTotal() throws IOException {
        int count = userdao.findTotal();

        System.out.println(count);
    }




}

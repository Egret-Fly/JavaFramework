package com.itheima.test;

import com.itheima.dao.UserDao;

import com.itheima.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的curd操作
 */
public class MybatisTest {

    private InputStream in;

    private SqlSession sqlSession;
    private UserDao userdao;
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory对象

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //获取dao的代理对象
        userdao = sqlSession.getMapper(UserDao.class);
    }
    @After
    public void destory() throws IOException {
        sqlSession.commit();
        sqlSession.close();
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
        user.setUsername("mybatis last saveuser");
        user.setAddress("西安市长安区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存之前"+user);
        //1保存
        userdao.saveUser(user);
        System.out.println("保存之后："+user);



    }







    /**
     * 测试id查询
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
     * 测试查询所有
     */
    @Test
    public void testFindByCondition() throws IOException {
        User u = new User();
        u.setUsername("老王");
        List<User> users = userdao.findUserByCondititon(u);

        for (User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 测试查询foreach所有
     */
    @Test
    public void testFindIds() throws IOException {
        QueryVo qv = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(46);
        qv.setIds(list);


        List<User> users = userdao.findUserInIds(qv);

        for (User user : users) {
            System.out.println(user);
        }

    }


}

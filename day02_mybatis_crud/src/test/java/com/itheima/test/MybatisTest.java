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
        user.setUsername("mybatis saveuser");
        user.setAddress("西安市长安区");
        user.setSex("男");
        user.setBirthday(new Date());
        //1保存
        userdao.saveUser(user);



    }
}

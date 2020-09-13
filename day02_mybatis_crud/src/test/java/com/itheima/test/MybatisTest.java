package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试mybatis的curd操作
 */
public class MybatisTest {

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {
        //1.读取配置文件,生成字节输入流
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory对象

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取dao的代理对象
        UserDao userdao = sqlSession.getMapper(UserDao.class);
        List<User> users = userdao.findAll();

        for (User user : users) {
            System.out.println(user);
        }

        //释放资源
        sqlSession.close();
        in.close();

    }
}

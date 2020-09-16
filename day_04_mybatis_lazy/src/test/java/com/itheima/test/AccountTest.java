package com.itheima.test;

import com.itheima.dao.AccountDao;

import com.itheima.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试mybatis的curd操作
 */
public class AccountTest {

    private InputStream in;

    private SqlSession sqlSession;
    private AccountDao accountdao;
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取sqlSessionFactory对象

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //获取dao的代理对象
        accountdao = sqlSession.getMapper(AccountDao.class);
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
        List<Account> accounts = accountdao.findAll();

        for (Account account : accounts) {
//            System.out.println("----------每个account的信息-----------");
//            System.out.println(account);
//            System.out.println(account.getUser());
        }

    }




}

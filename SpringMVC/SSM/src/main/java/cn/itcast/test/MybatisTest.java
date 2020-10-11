package cn.itcast.test;

import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    @Test
    public void Test1() throws Exception {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSession session = new SqlSessionFactoryBuilder().build(in).openSession();
        AccountDao accountdao = session.getMapper(AccountDao.class);
        List<Account> all = accountdao.findAll();
        for (Account account : all) {

            System.out.println(account);
        }
        session.close();
        in.close();
    }

    @Test
    public void Test2() throws Exception {

        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSession session = new SqlSessionFactoryBuilder().build(in).openSession();
        AccountDao accountdao = session.getMapper(AccountDao.class);
        Account account = new Account();
        account.setName("test");
        account.setMoney(5000f);
        accountdao.save(account);

        session.commit();

        session.close();
        in.close();
    }
}

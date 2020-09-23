package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试，测试我们的配置
 */
public class AccountServiceTest {
    @Test
    public void testFindAll() {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        List<Account> accounts = as.FindAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
            
        }
    }

    @Test
    public void testFindOne() {

        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        Account accounts = as.findAccountById(1);

            System.out.println(accounts);


    }


    @Test
    public void testSave() {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        Account account = new Account();
        account.setName("wang");
        account.setMoney(10000f);
         as.saveAccount(account);


    }

    @Test
    public void testUpdate() {

        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);
        Account account = new Account();
        account.setId(4);
        account.setName("zhang");
        account.setMoney(10000f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        AccountService as = ac.getBean("accountService",AccountService.class);


        as.deleteAccount(4);
    }



}

package com.itheima.ui;

import com.itheima.dao.AccountDao;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("Bean.xml");
        //2.根据id获取Bean对象
        AccountService as = (AccountService) ac.getBean("accountService3");
        as.saveaccount();
    }
}

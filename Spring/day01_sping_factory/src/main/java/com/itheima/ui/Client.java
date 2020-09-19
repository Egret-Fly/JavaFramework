package com.itheima.ui;

import com.itheima.dao.AccountDao;
import com.itheima.factory.BeanFactory;
import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {
    public static void main(String[] args) {
        //AccountService as = (AccountService) BeanFactory.getBean("accountService");
        for (int i=0;i<5;i++)
        {
            AccountService as = (AccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveaccount();
        }
        //as.saveaccount();
    }
}

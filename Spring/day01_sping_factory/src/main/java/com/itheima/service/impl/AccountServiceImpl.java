package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;
import com.itheima.factory.BeanFactory;
import com.itheima.service.AccountService;


/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = (AccountDao) BeanFactory.getBean("accountDao");
    private int i = 1;
    public void saveaccount() {
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}

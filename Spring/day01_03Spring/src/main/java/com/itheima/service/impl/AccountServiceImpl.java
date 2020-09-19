package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;

import com.itheima.service.AccountService;


/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {
    private static AccountDao accountDao = new AccountDaoImpl();

    public void saveaccount() {
        accountDao.saveAccount();

    }
}

package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import com.itheima.service.AccountService;

import java.util.List;

/**
 * 账户的业务层实现类
 */

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public List<Account> FindAllAccount() {
      return   accountDao.FindAllAccount();
    }


    public Account findAccountById(Integer id) {
       return accountDao.findAccountById(id);
    }

    public void saveAccount(Account accountcc) {
        accountDao.saveAccount(accountcc);

    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

    }

    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }
}

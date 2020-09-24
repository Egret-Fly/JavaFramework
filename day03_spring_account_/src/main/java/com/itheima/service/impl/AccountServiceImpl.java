package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.utils.TransactionManager;

import java.util.List;

/**
 * 账户的业务层实现类
 *
 * 事务控制应该都在业务层
 */
public class AccountServiceImpl implements IAccountService{

    private IAccountDao accountDao;
    private TransactionManager txManger;

    public void setTxManger(TransactionManager txManger) {
        this.txManger = txManger;
    }

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAllAccount() {
        try {
            //1.开启事务
            //2.执行操作
            //3.提交事务
            //4.返回结果
        }catch (Exception e){
            //回滚操作
        }finally {
            //6.释放连接
        }
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            //1.开启事务
            //2.执行操作
            //3.提交事务
            //4.返回结果
        }catch (Exception e){
            //回滚操作
        }finally {
            //6.释放连接
        }
        return accountDao.findAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer acccountId) {
        accountDao.deleteAccount(acccountId);
    }

    @Override
    public void transfer(String sourceName, String targetName, Float money) {
        //1.根据名称查询转出账户
        Account source= accountDao.findAccountByName(sourceName);
        //2.根据名称查询转入账户
        Account target= accountDao.findAccountByName(targetName);
        //3.转出账户减钱
        source.setMoney(source.getMoney()-money);
        //4.转入账户加钱
        target.setMoney(target.getMoney()+money);
        //5.更新转出账户
        accountDao.updateAccount(source);
        //6.更新转入账户
        accountDao.updateAccount(target);
    }


}

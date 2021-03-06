package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class AccountDaoImpl implements AccountDao {
    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    private QueryRunner runner;

    public List<Account> FindAllAccount() {
        try {
            return runner.query("select * from account",new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public Account findAccountById(Integer id) {
        try {
            return runner.query("select * from account where id = ? ",new BeanHandler<Account>(Account.class),id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void saveAccount(Account accountcc) {
        try {
             runner.update("insert into account (name ,money)  values(?,?)",accountcc.getName(),accountcc.getMoney());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            runner.update("update  account set name=? ,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from account where id=?",accountId);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

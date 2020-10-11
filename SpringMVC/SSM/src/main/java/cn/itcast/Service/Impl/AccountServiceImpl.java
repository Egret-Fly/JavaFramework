package cn.itcast.Service.Impl;

import cn.itcast.Service.AccountService;
import cn.itcast.dao.AccountDao;
import cn.itcast.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 业务层
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
         return  accountDao.findAll();
    }

    @Override
    public void save(Account account){
        accountDao.save(account);
        System.out.println("findById执行了");
    }
}

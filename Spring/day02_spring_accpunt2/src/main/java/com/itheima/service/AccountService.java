package com.itheima.service;

import com.itheima.domain.Account;

import java.util.List;

/**
 *
 * 账户的业务层接口
 */
public interface AccountService {

    /**
     * 查询所有
     * @return
     */
    List<Account> FindAllAccount();

    /**
     * 查询一个
     * @return
     */
    Account findAccountById(Integer id);

    /**
     * 保存操作
     * @param accountcc
     */
    void saveAccount(Account accountcc);

    /**
     * 更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     * @param accountId
     */

    void deleteAccount(Integer accountId);
}

package com.itheima.service;

import org.springframework.stereotype.Component;

/**
 * 账户业务层的接口
 *
 *  <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
 */


public interface AccountService {

    /**
     * 模拟保存账户
     */
    void saveaccount();
}

package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.dao.impl.AccountDaoImpl;

import com.itheima.service.AccountService;

import java.util.Date;


/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name,Integer age,Date birthday){
        this.name=name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveaccount() {
        System.out.println("service中的saveAccount方法执行了....."+name+","+age+","+birthday);
    }
}

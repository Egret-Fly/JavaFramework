package com.itheima.service.impl;

import com.itheima.service.AccountService;

import java.util.*;


/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl3 implements AccountService {

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String,String> myMap;
    private Properties myProps;

    public void setMyStrs(String[] name) {
        this.myStrs = name;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    public void saveaccount() {
        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(myMap);
        System.out.println(myProps);
        System.out.println(mySet);
    }
}

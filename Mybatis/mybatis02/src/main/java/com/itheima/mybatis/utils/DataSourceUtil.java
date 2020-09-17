package com.itheima.mybatis.utils;

import com.itheima.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceUtil {
    public static Connection getConnection(Configuration cfg){
        /**
         * 用于获取连接
         */
        try{
        Class.forName(cfg.getDriver());
        return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());}
        catch(Exception e){
            throw new RuntimeException(e);
        }

    }
}

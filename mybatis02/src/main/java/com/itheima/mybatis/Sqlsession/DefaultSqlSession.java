package com.itheima.mybatis.Sqlsession;

import com.itheima.mybatis.Sqlsession.proxy.MapperProxy;
import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class DefaultSqlSession implements SqlSession{
    private Configuration cfg;
    private Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg=cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    //用于创建代理对象
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));
        return null;
    }
    //用于释放资源
    public void close() {
        if (connection!=null){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }}
    }
}

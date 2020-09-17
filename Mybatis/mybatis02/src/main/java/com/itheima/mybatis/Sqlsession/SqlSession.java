package com.itheima.mybatis.Sqlsession;

/**
 * 自定义Mybatis中和数据库交互的核心类
 * 他可以创建dao接口的代理对象
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass dao的字节码
     * @param <T>
     * @return
     */

    public <T> T getMapper(Class<T> daoInterfaceClass);

    void close();
}

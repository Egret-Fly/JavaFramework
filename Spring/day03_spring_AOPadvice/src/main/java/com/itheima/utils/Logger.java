package com.itheima.utils;


/**
 * 用于记录日志的工具类，他提供了公共代码（切入点方法就是业务方法）
 */
public class Logger {
    /**
     * 前置通知
     */
    public  void beforeprintLog(){
        System.out.println("前置通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 后置通知
     */
    public  void afterReturningprintLog(){
        System.out.println("后置通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 异常通知
     */
    public  void afterThrowingprintLog(){
        System.out.println("异常通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 最终通知
     */
    public  void afterprintLog(){
        System.out.println("最终通知Logger类中的printlog方法开始记录日志了");
    }
}

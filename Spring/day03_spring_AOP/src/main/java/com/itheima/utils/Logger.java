package com.itheima.utils;


/**
 * 用于记录日志的工具类，他提供了公共代码（切入点方法就是业务方法）
 */
public class Logger {
    /**
     * 用于打印日志：计划让在其切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    public  void printLog(){
        System.out.println("Logger类中的printlog方法开始记录日志了");
    }
}

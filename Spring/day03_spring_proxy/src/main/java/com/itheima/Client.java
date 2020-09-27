package com.itheima;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        final Producer producer = new Producer();


        IProducer proxyProducer = (IProducer) Proxy.newProxyInstance(producer.getClass().getClassLoader(),
                Producer.class.getInterfaces(),
                new InvocationHandler() {

                    /**
                     * 作用：执行被代理对象的任何接口方法都会经过该方法
                     *
                     * @param proxy  代理对象的引用
                     * @param method 当前执行的方法
                     * @param args   当前执行方法所需的参数-
                     * @return 和被代理对象方法有相同的返回值
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //提供增强的代码
                        Object returnValue = null;
                        //获取方法执行的参数
                        Float money = (Float) args[0];
                        //判断当前方法是不是销售
                        if ("saleProduct".equalsIgnoreCase(method.getName()))
                            returnValue = method.invoke(producer, money * 0.8f);
                        return returnValue;
                    }
                });

        proxyProducer.saleProduct(10000f);

    }
}

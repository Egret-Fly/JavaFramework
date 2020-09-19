package com.itheima.ui;

import com.itheima.dao.AccountDao;

import com.itheima.service.AccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;

/**
 * 模拟一个表现层，用于调用业务层
 *
 */
public class Client {
    /**
     * 获取Spring的IOc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类
         * ClassPathXmlApplicationContext：他可以加载类路径下的配置文件，要求配置文件必须在类路径下，不在的话，加载不了
         * FileSystemXmlApplicationContext：他可以加载磁盘任意路劲下的配置文件
         * AnnotationConfigApplicationContext：它是用于读取注解，创建文件
     * 核心容器的两个接口引发出的问题
     * ApplicationContext：
     *      ta在构建核心容器时，创建对象采取的策略是采用立即加载的方式，也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象。
     * BeanFactory:
     *      它在创建核心容器时，创建对象采取的策略是延迟加载的，也就是说，什么时候id获取对象，什么时候才真正创建对象
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac=new ClassPathXmlApplicationContext("Bean.xml");
        //2.根据id获取bean对象
        AccountService as = (AccountService) ac.getBean("accountService");
        AccountDao adao = ac.getBean("accountDao",AccountDao.class);
        //           as.saveaccount();
        System.out.println(as);
        System.out.println(adao);

    }
}

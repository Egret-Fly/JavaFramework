package cn.itcast.test;

import cn.itcast.Service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceTest {
    @Test
    public void Test1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        AccountService as =(AccountService) ac.getBean("accountService");
        as.findAll();
    }
}

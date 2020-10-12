package test;

import cn.itcast.dao.Itemsdao;
import cn.itcast.domain.Items2;
import com.sun.tools.javac.jvm.Items;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Items2Test {
    @Test
    public void Test(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Itemsdao as = ac.getBean(Itemsdao.class);
        Items2 byId = as.findById(1);
        System.out.println(byId);


    }
}

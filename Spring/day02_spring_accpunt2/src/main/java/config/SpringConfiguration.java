package config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 该类是一个配置类，它的作用和bean.xml是一样的
 * spring中的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 *      细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写。
 * ComponentScan
 *      作用：用于通过注释指定spring在创建容器时要扫描的包
 *      属性：
 *          value:他和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *                  我们使用使用注解就等同于在xml中配置了
 *
 *                  <!--    告知spring在创建容器时要扫描的包-->
 *     <context:component-scan base-package="com.itheima"></context:component-scan>
 *
 * Bean
 * 作用：用于把当前方法的返回值作为bean对象存入spring的容器中
 * 属性：
 *      用于指定bean的id。当不写时，默认值是当前方法的名称
 *  细节：
 *      当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有可用的bean对象
 *      查找的方式和Autowired注解的作用是一样的
 */
@Configuration
@ComponentScan(basePackages = "com.itheima")
public class SpringConfiguration {

    /**
     * 用于创建一个QueryRunner对象
     */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     *创建数据源对象
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDateSource(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/xd20192");
            ds.setUser("root");
            ds.setPassword("root");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return ds;
    }
}

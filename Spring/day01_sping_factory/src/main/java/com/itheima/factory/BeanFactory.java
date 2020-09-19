package com.itheima.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *一个创建Bean对象的工厂
 *
 * Bean:可重用组件
 * JavaBean:用java语言编写的可重用组件
 *
 * 第一个：需要一个配置文件来配置我们的service和dao
 *      配置的内容：唯一标识=全限定类名(key=value)
 * 第二个：通过读取配置文件中配置的内容，反射创建对象
 *
 * 配置文件可以是xml也可以是properties
 */
public class BeanFactory {
    //定义一个Properties对象
    private static Properties props;

    //定义一个Map，用于存放我们要创建的对象，我们把它称之为容器
    private static Map<String,Object> beans;

    //使用静态代码块为Properties对象赋值
    static {
        try{
            //实例化对象
            props = new Properties();
            //获取properties的文件流对象
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);
            //实例化容器
            beans = new HashMap<String, Object>();
            //取出配置文件所有的key
            Enumeration keys = props.keys();
            //遍历枚举
            while (keys.hasMoreElements()){
                //取出每个key
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath  = props.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key,value);
            }


        }catch (Exception e){
            throw new ExceptionInInitializerError("初始化properties失败");
        }
    }

    /**
     * 根据Bean名称获取对象
     * @param Beanname
     * @return
     */
    public static Object getBean(String Beanname){
            return beans.get(Beanname);
    }

/*
    */
/**
     * 根据Bean名称获取Bean对象
     * @param Beanname
     * @return
     *//*

    public static Object getBean(String Beanname){
        Object bean = null;
        try {
            String beanPath = props.getProperty(Beanname);
            bean = Class.forName(beanPath).newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;

    }
*/

}

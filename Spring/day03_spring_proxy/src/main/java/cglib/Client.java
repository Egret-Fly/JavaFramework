package cglib;

import com.itheima.IProducer;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        final Producer producer = new Producer();

        Producer proxyProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param o
             * @param method
             * @param objects
             * //以上三个参数和基于接口的动态代理中invoke方法是一样的
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                //提供增强的代码
                Object returnValue = null;
                //获取方法执行的参数
                Float money = (Float) objects[0];
                //判断当前方法是不是销售
                if ("saleProduct".equalsIgnoreCase(method.getName()))
                    returnValue = method.invoke(producer, money * 0.8f);
                return returnValue;
            }
        });
        proxyProducer.saleProduct(10000);


    }
}

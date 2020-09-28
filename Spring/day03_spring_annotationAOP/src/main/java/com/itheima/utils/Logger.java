package com.itheima.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 用于记录日志的工具类，他提供了公共代码（切入点方法就是业务方法）
 */
@Component("logger")
@Aspect//表示当前类是个切面类
public class Logger {
    @Pointcut("execution(* com.itheima.Service.impl.*.*(..))")
    private void pt1(){
    }

    /**
     * 前置通知
     */
    @Before("pt1()")
    public  void beforeprintLog(){
        System.out.println("前置通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 后置通知
     */
    @AfterReturning("pt1()")
    public  void afterReturningprintLog(){
        System.out.println("后置通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 异常通知
     */
    @AfterThrowing("pt1()")
    public  void afterThrowingprintLog(){
        System.out.println("异常通知Logger类中的printlog方法开始记录日志了");
    }
    /**
     * 最终通知
     */
    @After("pt1()")
    public  void afterprintLog(){
        System.out.println("最终通知Logger类中的printlog方法开始记录日志了");
    }

    /**
     * 环绕通知
     * 问题：
     * 当我们配置了环绕通知后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     * 通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入方法调用，而我们的代码没有
     * 解决：
     * Spring框架为我们提供了一个接口：ProceedingJoinPoint.该接口有一个方法proceed(),此方法就相当于明确调用切入点方法。
     *该接口可以作为环绕通知的方法参数，在程序执行时，spring框架为我们提供该接口的实现类供我们使用
     *
     * spring中的环绕通知：
     *  它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    //@Around("pt1()")
    public  Object arroundPrintLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
           Object[] args = pjp.getArgs();//得到方法执行所需的参数
           rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
            System.out.println("环绕Logger类中的printlog方法开始记录日志了");
            return rtValue;
       }catch (Throwable t){
            throw  new RuntimeException(t);
       }finally {

       }



    }
}

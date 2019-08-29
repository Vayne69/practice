package com.example.practice.aopAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 19:40
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class AopAspect {
    /**
     * 定义一个切入点表达式,用来确定哪些类需要代理
     * execution(* aopdemo.*.*(..))代表aopdemo包下所有类的所有方法都会被代理
     */
    @Pointcut("execution(* com.example.practice.aopAspect.*.*(..))")
    public void declare() {
    }

    /**
     * 前置方法,在目标方法执行前执行
     *
     * @param joinPoint 封装了代理方法信息的对象,若用不到则可以忽略不写
     */
    @Before("declare()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("目标方法名为：" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名：" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型：" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        Object[] args = joinPoint.getArgs();
        log.info(String.valueOf(args.length));
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i + 1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

    }

    /**
     * 环绕方法,可自定义目标方法执行的时机
     *
     * @param pjd JoinPoint的子接口,添加了
     *            Object proceed() throws Throwable 执行目标方法
     *            Object proceed(Object[] var1) throws Throwable 传入的新的参数去执行目标方法
     *            两个方法
     * @return 此方法需要返回值, 返回值视为目标方法的返回值
     */
    @Around("declare()")
    public Object aroundMethod(ProceedingJoinPoint pjd) {
        Object result = null;

        try {
            //前置通知
            System.out.println("目标方法执行前...");
            //执行目标方法
            result = pjd.proceed();
            //用新的参数值执行目标方法
            // result = pjd.proceed(new Object[]{"newSpring","pppp"});
            //返回通知
            System.out.println("目标方法返回结果后...");
        } catch (Throwable e) {
            //异常通知
            System.out.println("执行目标方法异常后...");
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("目标方法执行后...");

        return result;
    }

}

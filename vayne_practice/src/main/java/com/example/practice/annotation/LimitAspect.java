package com.example.practice.annotation;

import com.example.practice.util.IPUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : Yang Jian
 * @date : 2019/8/29 15:41
 */
@Aspect
@Configuration
public class LimitAspect {
    //根据ip分不同的令牌桶，每天自动清理缓存
    private static LoadingCache<String, RateLimiter> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build(new CacheLoader<String, RateLimiter>() {
                @Override
                public RateLimiter load(String key) throws Exception {
                    //新的ip初始化，每秒只发出5个令牌
                    return RateLimiter.create(5);
                }
            });

    @Pointcut("@annotation(com.example.practice.annotation.ServiceLimit)")
    public void serviceAspect() {

    }

    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ServiceLimit limitAnnotation = method.getAnnotation(ServiceLimit.class);
        ServiceLimit.LimitType limitType = limitAnnotation.limitType();
        String key = limitAnnotation.key();
        Object obj;
        try {
            if (ServiceLimit.LimitType.IP.equals(limitType)) {
                key = IPUtils.getIpAddr();
            }
            RateLimiter rateLimiter = cache.get(key);
            boolean flag = rateLimiter.tryAcquire();
            if (flag) {
                obj = joinPoint.proceed();
            } else {
                throw new RuntimeException("小同志，你访问的太频繁了");
            }
        } catch (Throwable e) {
            throw new RuntimeException("小同志，你访问的太频繁了");
        }
        return obj;
    }
}

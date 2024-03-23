package com.avgkin.tacocloudplusserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.avgkin.tacocloudplusserver.controller..*(..))")
    public void beforeLog(JoinPoint joinPoint){
        log.info("请求传入："+ Arrays.toString(joinPoint.getArgs()));
    }
    @AfterReturning(value = "execution(* com.avgkin.tacocloudplusserver.controller..*(..))",returning = "returnValue")
    public Object afterLog(JoinPoint joinPoint,Object returnValue){
        log.info("返回响应:"+ returnValue.toString());
        return returnValue;
    }

//    @Before("execution(* org.redisson.Redisson*(..))")
//    public void redisWriteLog(JoinPoint joinPoint){
//        log.info("redis：操作"+joinPoint.getSignature().toString());
//    }
}

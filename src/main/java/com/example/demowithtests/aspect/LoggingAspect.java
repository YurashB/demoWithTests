package com.example.demowithtests.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.example.demowithtests.service.*.*(..))")
    public void beforeMethodLoggingAdvice(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] arguments = joinPoint.getArgs();

        log.info("Method: " + className + "." + methodName + " - start: values = " + Arrays.toString(arguments));
    }

    @AfterReturning(value = "execution(* com.example.demowithtests.service.*.*(..))", returning = "retVal")
    public void afterReturningMethodLoggingAdvice(JoinPoint joinPoint, Object retVal) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] arguments = joinPoint.getArgs();

        log.info("Method: " + className + "." + methodName + " - end: returned values = " + retVal.toString());
    }


    /*
    Same method advice but in one method
     */

//    @Around("execution(* com.example.demowithtests.service.*.*(..))")
//    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getTarget().getClass().getName();
//        Object[] arguments = joinPoint.getArgs();
//
//        log.info("Method: " + className + "." + methodName + " - start: values = " + Arrays.toString(arguments));
//
//        Object returnedByMethod = joinPoint.proceed();
//
//        log.info("Method: " + className + "." + methodName + " - end: returned values = " + returnedByMethod.toString());
//
//        return returnedByMethod;
//    }
}

package com.wa.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TestAspect
 * 2023/2/12 3:31 下午
 *
 * @author wuao
 */
@Component
@Aspect
public class TestAspect {

    @Pointcut("execution(* com.wa.spring.service.impl.*.get*(..))")
    private void pointcut() {

    }

    // 前置通知
    @Before("pointcut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.printf("TestAspect beforeAdvice method, joinPoint{%s}\n", joinPoint.toString());
    }


    //后置通知：方法正常执行后，有返回值，执行该后置通知：如果该方法执行出现异常，则不执行该后置通知
    @AfterReturning(value = "pointcut()", returning = "returnVal")
    public void afterReturningAdvice(JoinPoint joinPoint, Object returnVal) {
        System.out.printf("TestAspect afterReturningAdvice method, joinPoint{%s}, returnVal{%s}\n",
                joinPoint.toString(), returnVal);
    }

    //后置通知
    @After("pointcut()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.printf("TestAspect afterAdvice method, joinPoint{%s}\n", joinPoint.toString());
    }

    //环绕通知
    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.printf("TestAspect aroundAdvice before method, joinPoint{%s}\n", joinPoint.toString());
        Object returnVale = joinPoint.proceed();
        System.out.printf("TestAspect aroundAdvice after method, joinPoint{%s}, returnVal{%s}\n",
                joinPoint.toString(), returnVale);
        return returnVale;
    }

    // 异常通知：方法出现异常时，执行该通知
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void throwAdvice(JoinPoint joinPoint, Exception ex) {
        System.out.printf("TestAspect throwAdvice before method, joinPoint{%s}, ex{%s}\n",
                joinPoint.toString(), ex);
        System.out.println("出现异常：" + ex.getMessage());
        System.out.printf("TestAspect throwAdvice after method, joinPoint{%s}, ex{%s}\n",
                joinPoint.toString(), ex);
    }
}

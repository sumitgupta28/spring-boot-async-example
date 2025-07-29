package com.spring.async.logging.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(public * com.spring.async.services.*.*(..))")
    private void allPublicServiceMethods() {
    }

    @Pointcut("execution(public * com.spring.async.controller.*.*(..))")
    private void allPublicControllerMethods() {
    }

    @Before("allPublicServiceMethods()")
    public void logBefore(JoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String methodName = signature.getMethod().getName();
        log.info("  logBefore {} ",methodName);

    }

    @AfterReturning("allPublicServiceMethods()")
    public void logAfterReturning(JoinPoint joinPoint)
    {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final String methodName = signature.getMethod().getName();
        log.info(" logAfterReturning {} ",methodName);
    }

    @Around("allPublicServiceMethods() || allPublicControllerMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed(); // Executes the advised method
        stopWatch.stop();
        log.info(" {}  executed in {} ns ", joinPoint.getSignature().getName(), stopWatch.getTotalTimeMillis());
        return result;
    }

}

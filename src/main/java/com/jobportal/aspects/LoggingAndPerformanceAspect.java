package com.jobportal.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAndPerformanceAspect {

    /**
     * Pointcut targets only controller and service layer methods.
     * This avoids logging every repository call, entity getter/setter, and utility method.
     */
    @Around("execution(* com.jobportal..controller..*(..)) || execution(* com.jobportal..service..*(..))")
    public Object logAndMeasureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        Object[] methodArgs = joinPoint.getArgs();
        
        log.info("➡️ Entering method: {}", methodName);
        log.debug("📥 Arguments: {}", Arrays.toString(methodArgs));
        
        Object result = joinPoint.proceed();
        
        long executionTime = System.currentTimeMillis() - startTime;
        log.info("✅ Method {} completed in {} ms", methodName, executionTime);
        
        return result;
    }
}

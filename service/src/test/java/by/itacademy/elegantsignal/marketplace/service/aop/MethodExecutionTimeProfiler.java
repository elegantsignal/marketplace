package by.itacademy.elegantsignal.marketplace.service.aop;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodExecutionTimeProfiler {

    private static final Logger LOGGER = LoggerFactory.getLogger("MethodExecutionTimeProfiler");

    @Pointcut("execution(* by.itacademy.elegantsignal.marketplace.service.impl.*.*(..))")
    public void serviceMethods() {
    }

    @Around("serviceMethods()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object output = pjp.proceed();
        long executionTime = System.nanoTime() - start;
        LOGGER.info("method execution: [{}:{}={} microseconds]", pjp.getTarget().getClass().getSimpleName(),
                pjp.getSignature().getName(), executionTime / 1000);
        return output;
    }
    
    @PostConstruct
    void init() {
    	System.out.println("Init finish");
    }

}

package com.loanapp.loanManagementSystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class Logging {

    public static final Logger log = LoggerFactory.getLogger(Logging.class);

    @Pointcut("execution(* com.loanapp.loanManagementSystem.service..*.*(..))")
    public void serviceLayer() {

    }

    @Pointcut("execution(* com.loanapp.loanManagementSystem.controllers ..*.*(..))")
    public void controllerLayer() {
    }

    @Before("serviceLayer()")
    public void logServiceEntry(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[SERVICE] Excecuting {}.{}() with arguments :{}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logServiceSuccess(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[SERVICE] Successfully executed {}.{}() with arguments : {}", className, methodName, Arrays.toString(args));

    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "exception")
    public void logServiceException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.error("[SERVICE] {}.{}() throws exception : {} - {} ",
                className, methodName, exception.getClass().getSimpleName(), exception.getMessage());

    }

    @Before("controllerLayer()")
    public void logControllerEntry(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("[CONTROLLER] Executing {}.{}() with arguments :{}", className, methodName, Arrays.toString(args));
    }

    @AfterReturning(value = "controllerLayer()", returning = "result")
    public void logControllerSuccess(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("[CONTROLLER] Excecuted Successfully {}.{}() ", className, methodName);
    }

    @AfterThrowing(value = "controllerLayer()", throwing = "exception")
    public void logControllerException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.error("[CONTROLLER] {}.{}() throws exception :{} - {}",
                className, methodName, exception.getClass().getSimpleName(), exception.getMessage());

    }
}

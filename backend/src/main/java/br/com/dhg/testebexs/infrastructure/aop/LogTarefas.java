package br.com.dhg.testebexs.infrastructure.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTarefas {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* br.com.dhg.testebexs.service.impl.*.*(..))")
    public void servicesPointcut() {}

    @Pointcut("execution(* br.com.dhg.testebexs.controller.*.*(..))")
    public void controllersPointcut() {}

    @Before("servicesPointcut()")
    public void servicesBefore(JoinPoint joinPoint) {
        logBefore(joinPoint.getSignature().getName());
    }

    @Before("controllersPointcut()")
    public void controllersBefore(JoinPoint joinPoint) {
        logBefore(joinPoint.getSignature().getName());
    }

    @After("servicesPointcut()")
    public void servicesAfter(JoinPoint joinPoint) {
        logAfter(joinPoint.getSignature().getName());
    }

    @After("controllersPointcut()")
    public void controllersAfter(JoinPoint joinPoint) {
        logAfter(joinPoint.getSignature().getName());
    }

    private void logBefore(String nome) {
        logger.info("Iniciando {}", nome);
    }

    private void logAfter(String nome) {
        logger.info("Finalizando {}", nome);
    }

}

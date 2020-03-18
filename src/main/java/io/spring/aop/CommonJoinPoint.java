package io.spring.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPoint {

    @Pointcut("execution(* io.spring.dao.*.*(..))")
    public void myDaoExecution() {

    }

    @Pointcut("@annotation(io.spring.aop.TrackTime)")
    public void trackTimeAnnotation() {

    }
}

package io.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AopAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AopAspect.class);

    //(* PACKAGE.*.*(..)) -> point cut
    // * = any return type
    // PACKAGE = in specific package
    // * = any class
    // * = all method
    // .. the arguments

    @Before("execution(* io.spring.dao.*.*(..))")
    public void before(JoinPoint joinPoint) {
        TimeTaken timeTaken = new TimeTaken(System.currentTimeMillis());
        LOGGER.info("Intercepted Method calls {}", joinPoint.toLongString());
    }

    @Before("io.spring.aop.CommonJoinPoint.myDaoExecution()")
    public void beforeUsingCommonJoinPointFromClass(JoinPoint joinPoint) {
        TimeTaken timeTaken = new TimeTaken(System.currentTimeMillis());
        LOGGER.info("Intercepted Method calls from defined point cut on class {}", joinPoint.toLongString());
    }


    @After("execution(* io.spring.dao.*.*(..))")
    public void after(JoinPoint joinPoint) {
        LOGGER.info("After method" );
    }

    @AfterReturning(value = "execution(* io.spring.dao.*.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        LOGGER.info("returning result {}", result );
    }

    @AfterThrowing(value = "execution(* io.spring.dao.*.*(..))", throwing = "ex")
    public void after(JoinPoint joinPoint, Exception ex) {
        LOGGER.info("throwing exception {}", ex.getMessage() );
    }


    //performance tracing (Around)

    /*
    change to use annotation
    @Around("execution(* io.spring.dao.*.*(..))")
    public void aroundForPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long taken = System.currentTimeMillis() - start;
        LOGGER.info("TimeTaken using around {}: {}ms",proceedingJoinPoint.toLongString(), taken );
    }*/

    @Around("io.spring.aop.CommonJoinPoint.trackTimeAnnotation()")
    public void aroundForPerformanceUsingAnnotationClass(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long taken = System.currentTimeMillis() - start;
        LOGGER.info("TimeTaken using around {}: {}ms",proceedingJoinPoint.toLongString(), taken );
    }





    class TimeTaken {
        long startTime;
        public TimeTaken(long startTime) {
            this.startTime = startTime;
        }
    }
}

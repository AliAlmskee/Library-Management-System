package library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* *(..)) && @annotation(Loggable)")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.info("Method called: {}", joinPoint.getSignature());
    }

    @AfterThrowing(pointcut = "execution(* *(..)) && @annotation(Loggable)", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("Exception occurred: {}", exception.getMessage());
    }

    @Around("execution(* *(..)) && @annotation(Loggable)")
    public Object logPerformanceMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        LOGGER.info("Method execution time: {}ms", endTime - startTime);
        return result;
    }
}
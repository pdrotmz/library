package io.github.pdrotmz.libraryAPI.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* io.github.pdrotmz.libraryAPI.service..*(..))")
    public void serviceLayer() {}

    @Before("serviceLayer()")
    public void logRequest(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        // Passa o array de argumentos diretamente para o LogUtils
        LOGGER.info(LogUtils.formatRequest(method, args));
    }

    @AfterReturning(pointcut = "serviceLayer()", returning = "result")
    public void logResponse(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();

        // Passa o resultado diretamente para o LogUtils
        LOGGER.info(LogUtils.formatResponse(method, result));
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void logError(JoinPoint joinPoint, Exception ex) {
        String method = joinPoint.getSignature().toShortString();
        LOGGER.error(LogUtils.formatError(method, ex), ex);
    }
}

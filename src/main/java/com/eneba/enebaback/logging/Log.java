package com.eneba.enebaback.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
@Aspect
@ConditionalOnExpression("${logging.enabled:true}")
public class Log {

    private Log() {
    }

    static java.util.logging.Logger logger = java.util.logging.Logger.getLogger("Aspect Logger");

    @AfterReturning(pointcut = "@annotation(com.eneba.enebaback.logging.Logging)", returning = "object")
    private static void log(JoinPoint joinPoint, Object object) throws IllegalAccessException {
        String methodMessage = getMethodMessage(joinPoint);
        logger.info("User: " + SecurityContextHolder.getContext().getAuthentication().getName()
                + " || Called method: " + joinPoint.getSignature() + " || With method message: " + methodMessage);
    }

    private static String getMethodMessage(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Logging aspectLogger = method.getAnnotation(Logging.class);
        return aspectLogger.value();
    }

}

package com.blog.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.blog.services.impl..*(..))", throwing = "exception")
    public void handleException(JoinPoint joinPoint, Exception exception) {
        // Log or perform any actions you need for exception handling

        StringBuilder builder = new StringBuilder("Error:");
        builder.append("(").append(joinPoint.getKind())
                .append(")\tfor: ").append(joinPoint.getSignature())
                .append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ','))
                .append(")")
                .append("\terror : ").append("(").append(exception).append(")");
        builder.append("\ttook: ");
        log.info("Exception handled by aspect: " + exception.getMessage());
        log.error(builder.toString());
    }
}

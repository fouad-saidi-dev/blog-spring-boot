package com.blog.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogTimeAspect {

    Logger log = LoggerFactory.getLogger(LogTimeAspect.class);

    @Before(value = "execution(* com.blog.services.impl..*(..))")
    public void logTime(JoinPoint joinPoint) {
      long startTime = System.currentTimeMillis();
      StringBuilder builder = new StringBuilder("KPI:");
      builder.append("(").append(joinPoint.getKind())
              .append(")\tfor: ").append(joinPoint.getSignature())
              .append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ','))
              .append(")");
      builder.append("\ttook: ");

      log.info(builder.append(System.currentTimeMillis() - startTime).append("ms.").toString());
    }
}

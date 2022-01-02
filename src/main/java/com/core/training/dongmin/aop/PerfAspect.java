package com.core.training.dongmin.aop;

import com.core.training.dongmin.data.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@Aspect
public class PerfAspect {
    @Autowired
    MessageSource messageSource;

    @Around("execution(void com.core.training.dongmin.observer.RegisteredUsers.messageObserver(String))")
    public Object beanLogPerf(ProceedingJoinPoint pjp) throws Throwable{
        Object proceed = pjp.proceed();
        log.info("{}",messageSource.getMessage("send.Message", new String[]{}, Locale.KOREA));
        return proceed;
    }
}

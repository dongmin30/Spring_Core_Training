package com.core.training.dongmin.aop;

import java.io.FileOutputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.Locale;

@Slf4j
@Component
@Aspect
public class PerfAspect {
    @Autowired
    MessageSource messageSource;

    @Around("execution(void com.core.training.dongmin.service.RegisterService.message*(*,*))")
    public Object beanLogPerf(ProceedingJoinPoint pjp) throws Throwable{
        Object proceed = pjp.proceed();
        try {
            OutputStream output = new FileOutputStream("");
            String str = LocalDate.now() + " << 관리자가 메세지를 보냈습니다. >>";
            byte[] by=str.getBytes();
            output.write(by);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return proceed;
    }
}

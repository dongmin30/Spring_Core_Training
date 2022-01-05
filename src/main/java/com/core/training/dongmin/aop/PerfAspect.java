package com.core.training.dongmin.aop;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Slf4j
@Component
@Aspect
public class PerfAspect {
    @Autowired
    MessageSource messageSource;

    @Autowired
    ApplicationContext resourceLoader;

    @Around("execution(void com.core.training.dongmin.service.RegisterService.message*(*,*))")
    public Object beanLogPerf(ProceedingJoinPoint pjp) throws Throwable{
        Object[] objects = pjp.getArgs();
        Object proceed = pjp.proceed();
        Resource resource = resourceLoader.getResource("classpath:tests/test.txt");

        String fileName = "C:/Users/newturn/IdeaProjects/Spring_Core_Training/src/main/resources/tests/test.txt";

        try{
            if(!resource.exists()){
                log.info("파일이 없습니다 {} 위치에 파일을 만듭니다.",resource.getURL());
            }
            else {
                log.info("파일이 있습니다 해당위치 {} 위치에 로그가 작성되었습니다.",resource.getURL());
            }

            // BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
            BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));


            // 파일안에 문자열 쓰기
            for (int i = 1; i < objects.length; i++){
                fw.write(LocalDateTime.now() + " :: << 관리자가 "+ objects[i] + "님에게 메세지를 보냈습니다. >>\n");
            }

            fw.flush();
            // 객체 닫기
            fw.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return proceed;
    }
}

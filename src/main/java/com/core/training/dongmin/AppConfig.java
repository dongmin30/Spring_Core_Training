package com.core.training.dongmin;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    //-- 메세지 소스 재선언
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //-- 메세지 프로퍼티파일의 위치와 이름을 지정
        messageSource.setBasename("classpath:/messages");

        //-- 기본인코딩 지정
        messageSource.setDefaultEncoding("UTF-8");

        //-- 프로퍼티 파일의 변경을 감지할 시간 간격 지정
        messageSource.setCacheMillis(10);

        //-- 없는 메세지일 경우 예외 발생대신 기본 메세지로 지정
        messageSource.setUseCodeAsDefaultMessage(false);

        return messageSource;
    }
}

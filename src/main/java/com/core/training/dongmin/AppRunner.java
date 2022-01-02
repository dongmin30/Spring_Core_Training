package com.core.training.dongmin;

import com.core.training.dongmin.data.User;
import com.core.training.dongmin.observer.RegisteredUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    ApplicationContext applicationContext;

    //-- 메세지소스
    @Autowired
    MessageSource messageSource;

    //-- 밸리데이션
    @Autowired
    Validator validator;

    //-- 이벤트 퍼블리셔
    @Autowired
    ApplicationEventPublisher publisher;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int input;
        boolean again = true;

        RegisteredUsers registeredUsers = new RegisteredUsers();
        User user = new User();

        //-- 입력 장치
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        while (again){
            System.out.println(messageSource.getMessage("switch.announcement", new String[]{}, Locale.KOREA));
            input = Integer.parseInt(bf.readLine());
            switch (input) {
                case 1:
                    //입력 작업
                    log.info("{}", messageSource.getMessage("spring.idQuestion", new String[]{}, Locale.KOREA));
                    user.setId(bf.readLine());

                    log.info("{}", messageSource.getMessage("spring.nameQuestion", new String[]{}, Locale.KOREA));
                    user.setName(bf.readLine());

                    log.info("{}", messageSource.getMessage("spring.ageQuestion", new String[]{}, Locale.KOREA));
                    user.setAge(bf.readLine());

                    //-- 밸리데이션 작업
                    Errors userErrors = new BeanPropertyBindingResult(user, "user");
                    validator.validate(user, userErrors);
                    userErrors.getAllErrors().forEach(err -> {
                        //-- 에러 발생 객체 경로
                        String around = err.getCodes()[0];
                        //-- 에러 메세지
                        String errMessage = err.getDefaultMessage();
                        log.error("ERROR AROUND : {}, ERROR MESSAGE : {}",around, errMessage);
                    });

                    //-- 밸리데이션 오류 체크
                    if (!userErrors.hasErrors()) {
                        registeredUsers.attach(user.getId());
                        if(registeredUsers.Users.size() != registeredUsers.Users.stream().distinct().count()){
                            log.error("{}",messageSource.getMessage("id.duplication", new String[]{}, Locale.KOREA));
                            again = false;
                        }
                        else {
                            //-- 이벤트 발급
                            publisher.publishEvent(new User(user.getId(), user.getName(), user.getAge()));
                        }
                    } else if (userErrors.hasErrors()) {
                        log.error("{}", messageSource.getMessage("spring.validationError", new String[]{}, Locale.KOREA));
                    }

                    break;

                case 2:
                    //-- 옵저버 패턴 형식의 메시지 보내기
                    registeredUsers.messageObserver("안녕하세요~ 공지입니다");
                    break;

                case 3:
                    again = false;
                    break;

                default:
                    System.out.println(messageSource.getMessage("send.Check", new String[]{}, Locale.KOREA));
                    break;
            }
        }
    }
}

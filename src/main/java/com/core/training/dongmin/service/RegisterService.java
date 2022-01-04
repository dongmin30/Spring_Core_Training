package com.core.training.dongmin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RegisterService {
    //-- 옵저버에 추가
    public void attach(String user, List<String> list){
        list.add(user);
    }

    //-- 옵저버에서 제거
    public void detach(String user, List<String> list){
        list.remove(user);
    }

    //-- 옵저버들에게 메세지 전달
    public void messageObserver(String msg, List<String> list){
        for (String o:list){
            log.info("{}님이 관리자에게 메세지를 받음 : {}",o , msg);
        }
    }
    public void messageObserverHello(String msg, List<String> list){
        for (String o:list){
            log.info("{}님이 관리자에게 인사 메시지를 받음 : {}",o , msg);
        }
    }

}

package com.core.training.dongmin.observer;

import com.core.training.dongmin.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class RegisteredUsers {
    public List<String> Users = new ArrayList<>();

    //-- 옵저버에 추가
    public void attach(String user){
        Users.add(user);
    }

    //-- 옵저버에서 제거
    public void detach(String user){
        Users.remove(user);
    }

    //-- 옵저버들에게 메세지 전달
    public void messageObserver(String msg){
        for (String o:Users){
            log.info("{}님이 관리자에게 메세지를 받음 : {}",o , msg);
        }
    }

    public void checkUsers(){
        log.info("{}",Users);
    }

}

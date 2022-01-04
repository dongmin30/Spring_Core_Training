package com.core.training.dongmin.event;

import com.core.training.dongmin.VO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegEventHandler {
    //-- 이벤트 리스너
    @EventListener
    @Async
    public void handle(User user){
        log.info("=============== REG EVENT HANDLER START ================");
        log.info("{} 님 가입하신걸 축하드립니다~", user.getName());
        log.info("=============== REG EVENT HANDLER END ================");
    }
}

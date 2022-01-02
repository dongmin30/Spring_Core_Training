package com.core.training.dongmin.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class EventHandler {

    @EventListener
    public void handle(ContextRefreshedEvent event){
        log.info("======= ContextRefreshedEvent =======");
    }

    @EventListener
    public void handle(ContextClosedEvent event){
        log.info("======= ContextClosedEvent =======");
    }

    @EventListener
    public void handle(ContextStartedEvent event){
        log.info("======= ContextStartedEvent =======");
    }

    @EventListener
    public void handle(ContextStoppedEvent event){
        log.info("======= ContextStoppedEvent =======");
    }
}

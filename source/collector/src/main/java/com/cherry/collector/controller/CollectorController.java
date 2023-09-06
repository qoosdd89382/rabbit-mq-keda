package com.cherry.collector.controller;

import com.cherry.collector.constant.RabbitMqConstants;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@CommonsLog
@RestController
@RequestMapping(path = "/collect")
public class CollectorController {

    @Autowired
    private RabbitMqConstants rabbitMqConstants;

    AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping(path = "/do")
    public void doSomething() throws InterruptedException {
        Thread.sleep(50);
        log.info("[" + atomicInteger.getAndIncrement() + "] do something...");
    }

    @GetMapping(path = "/getConfig")
    public String getConfig() {
        return rabbitMqConstants.getExchangeName();
    }


}

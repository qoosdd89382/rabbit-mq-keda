package com.cherry.collector.controller;

import com.cherry.collector.constant.RabbitMqConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/collect")
public class CollectorController {

    @Autowired
    private RabbitMqConstants rabbitMqConstants;

    @GetMapping(path = "/do")
    public void doSomething() {
        System.out.println("do something...");
    }

    @GetMapping(path = "/getConfig")
    public String getConfig() {
        return rabbitMqConstants.getExchangeName();
    }


}

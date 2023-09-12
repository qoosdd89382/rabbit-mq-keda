package com.cherry.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * Rabbit MQ 監聽連不上時依然可以接收請求
     * @return
     */
    @GetMapping(path = "/test")
    public String test() {
        return "alive";
    }
}

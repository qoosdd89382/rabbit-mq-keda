package com.cherry.consumer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "collector-client")
public interface CollectorClient {

    @GetMapping("/collect/getConfig")
    String test();

    @GetMapping("/collect/do")
    void doSomething();

    @PostMapping("/collect/printer")
    void printer(@RequestParam String message, @RequestParam String from);

}

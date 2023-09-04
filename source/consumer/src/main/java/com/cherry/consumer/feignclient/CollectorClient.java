package com.cherry.consumer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "collector-client")
public interface CollectorClient {

    @GetMapping("/collect/getConfig")
    String test();

    @GetMapping("/collect/do")
    void doSomething();

}

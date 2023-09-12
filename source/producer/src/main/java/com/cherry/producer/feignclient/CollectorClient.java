package com.cherry.producer.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "consumer-client")
public interface CollectorClient {

    @GetMapping("/consume/notifier")
    void notifier();

}

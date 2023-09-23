package com.cherry.producer.controller;


import com.cherry.producer.generator.UUIDGenerator;
import com.cherry.producer.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private Producer producer;

    @GetMapping("/send")
    public String testSend() {
        producer.send("this is a test message, time=" + java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            + ",uuid=" + UUIDGenerator.generateUUID()
        );
        return "test MQ sender demo";
    }

}
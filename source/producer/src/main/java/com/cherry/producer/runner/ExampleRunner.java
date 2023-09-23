package com.cherry.producer.runner;


import com.cherry.producer.generator.UUIDGenerator;
import com.cherry.producer.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ExampleRunner implements ApplicationRunner {

    @Autowired
    private Producer producer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        while (true) {
            producer.send("this is a test message, time=" + java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    + ", uuid=" + UUIDGenerator.generateUUID()
            );
        }
    }
}
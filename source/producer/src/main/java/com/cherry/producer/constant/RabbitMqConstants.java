package com.cherry.producer.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rabbit-mq-constants")
public class RabbitMqConstants {

    private String exchangeName;
    private String routingKeyName;
    private String queueName;
    private Reconnect reconnect;

    @Getter
    @Setter
    public static class Reconnect {
        private Integer initialSeconds;
        private Integer periodSeconds;
    }
}

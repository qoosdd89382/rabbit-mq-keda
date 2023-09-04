package com.cherry.producer.constant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "rabbit-mq-constants")
public class RabbitMqConstants {

    private String exchangeName;
    private String routingKeyName;
    private String queueName;

}

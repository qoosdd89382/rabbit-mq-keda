package com.cherry.producer.task;

import com.cherry.producer.constant.RabbitMqConstants;
import com.cherry.producer.enumeration.QExchangeStatus;
import com.cherry.producer.producer.QStatusHolder;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class QExchangeReconnectTaskManager {

    private Timer timer;
    private QExchangeReconnectTask task;

    private final static long SEC_MILLS = 1000;

    @Autowired
    private QStatusHolder qStatusHolder;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitMqConstants rabbitMqConstants;

    public void start() {
        if (timer != null || qStatusHolder.getQueueStatus() != QExchangeStatus.FAIL_OVER) {
            return;
        }
        timer = new Timer();
        task = new QExchangeReconnectTask(this);
        timer.schedule(task,
                this.getInitialSeconds() * SEC_MILLS,
                this.getPeriodSeconds() * SEC_MILLS);
    }

    private long getInitialSeconds() {
        return rabbitMqConstants.getReconnect()
                .getInitialSeconds();
    }

    private long getPeriodSeconds() {
        return rabbitMqConstants.getReconnect()
                .getPeriodSeconds();
    }

    public void stop() {
        if (timer == null) {
            return;
        }
        timer.cancel();
        timer.purge();
        timer = null;
    }

    public QExchangeStatus getQueueStatus() {
        return qStatusHolder.getQueueStatus();
    }

    public boolean isRabbitUp() {
        try (Connection connection = connectionFactory.createConnection()) {
            return connection.isOpen();
        } catch (AmqpException e) {
            return false;
        }
    }

}

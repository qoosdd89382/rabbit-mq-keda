package com.cherry.producer.producer;

import com.cherry.producer.enumeration.QExchangeStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.ConnectException;

/**
 * 多pod時狀態存在其他地方比較好
 */
@Component
public class QStatusManager {

    @Autowired
    private ConnectionFactory connectionFactory;

    private QExchangeStatus qExchangeStatus = QExchangeStatus.FAIL_OVER;

    @PostConstruct
    public void init() {
        qExchangeStatus = this.isRabbitUp()
                ? QExchangeStatus.UP
                : QExchangeStatus.FAIL_OVER;
        System.out.println("===init result: " + qExchangeStatus.name() + " ===");
    }

    public QExchangeStatus getQueueStatus() {
        return qExchangeStatus;
    }

    public synchronized void setQueueStatus(QExchangeStatus qExchangeStatus) {
        this.qExchangeStatus = qExchangeStatus;
        System.out.println("===set status: " + qExchangeStatus.name() + " ===");
    }

    public boolean isRabbitUp() {
        try (Connection connection = connectionFactory.createConnection()) {
            return connection.isOpen();
        } catch (AmqpException e) {
            return false;
        }
    }
}

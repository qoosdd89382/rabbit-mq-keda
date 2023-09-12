package com.cherry.producer.task;

import com.cherry.producer.constant.RabbitMqConstants;
import com.cherry.producer.enumeration.QExchangeStatus;
import com.cherry.producer.producer.QStatusHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;

public class QExchangeReconnectTask extends TimerTask {

    QExchangeReconnectTaskManager qExchangeReconnectTaskManager;

    public QExchangeReconnectTask(QExchangeReconnectTaskManager qExchangeReconnectTaskManager) {
        this.qExchangeReconnectTaskManager = qExchangeReconnectTaskManager;
    }

    @Override
    public void run() {
        System.out.println("START");
        if (qExchangeReconnectTaskManager.getQueueStatus() != QExchangeStatus.FAIL_OVER) {
            return;
        }
        System.out.println("!!!");

        if (qExchangeReconnectTaskManager.isRabbitUp()) {
            System.out.println("is running");
            qExchangeReconnectTaskManager.stop();
        }
    }
}

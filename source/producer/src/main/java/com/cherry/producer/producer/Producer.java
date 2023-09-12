package com.cherry.producer.producer;

import com.cherry.producer.constant.RabbitMqConstants;
import com.cherry.producer.dao.QFailOverDao;
import com.cherry.producer.enumeration.QExchangeStatus;
import com.cherry.producer.exception.QFailOverException;
import com.cherry.producer.feignclient.CollectorClient;
import com.cherry.producer.po.QFailOverPo;
import com.cherry.producer.task.QExchangeReconnectTaskManager;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private RabbitMqConstants rabbitMqConstants;

    @Autowired
    private QExchangeReconnectTaskManager qExchangeReconnectTaskManager;

    @Autowired
    private QFailOverDao qFailOverDao;

    @Autowired
    private CollectorClient collectorClient;

    public void send(String message) {
        try {
            if (qExchangeReconnectTaskManager.getQueueStatus() == QExchangeStatus.FAIL_OVER) {
                System.out.println("已進入failover機制");
                throw new QFailOverException();
            }

            template.convertAndSend(
                    rabbitMqConstants.getExchangeName(),
                    rabbitMqConstants.getRoutingKeyName(),
                    message);

            System.out.println(" Sent '" + message + "'");
        } catch (AmqpException  | QFailOverException e ) {
            System.out.println(" [x] Error sending message: " + e.getMessage());
            qExchangeReconnectTaskManager.start();

            QFailOverPo po = new QFailOverPo();
            po.setValue(message);
            po.setIsConsumed(false);
            qFailOverDao.save(po);

            collectorClient.notifier();
        }
    }

}
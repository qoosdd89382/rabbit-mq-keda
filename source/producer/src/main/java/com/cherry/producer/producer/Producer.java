package com.cherry.producer.producer;

import com.cherry.producer.constant.RabbitMqConstants;
import com.cherry.producer.dao.QFailOverDao;
import com.cherry.producer.enumeration.QExchangeStatus;
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
    private QStatusHolder QStatusHolder;

    @Autowired
    private QExchangeReconnectTaskManager qExchangeReconnectTaskManager;

    @Autowired
    private QFailOverDao qFailOverDao;

    @Autowired
    private CollectorClient collectorClient;

    public void send(String message) {
        try {
            template.convertAndSend(
                    rabbitMqConstants.getExchangeName(),
                    rabbitMqConstants.getRoutingKeyName(),
                    message);

            System.out.println(" Sent '" + message + "'");
        } catch (AmqpException e) {
            System.out.println(" [x] Error sending message: " + e.getMessage());

            QStatusHolder.setQueueStatus(QExchangeStatus.FAIL_OVER);
            qExchangeReconnectTaskManager.start();

            QFailOverPo po = new QFailOverPo();
            po.setValue(message);
            po.setIsConsumed(false);
            qFailOverDao.save(po);

            collectorClient.notifier();
        }
    }

}
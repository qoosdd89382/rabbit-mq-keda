package com.cherry.consumer.consumer;

import com.cherry.consumer.enumeration.QQqueueStatus;
import com.cherry.consumer.feignclient.CollectorClient;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

@Component
public class Consumer {

    @Autowired
    private CollectorClient collectorClient;

    @RabbitListener(queues = "${rabbit-mq-constants.queue-name}")
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String receivedMessage = new String(message.getBody());
            System.out.println(" [new]Receiver1 Received '" + receivedMessage + "'");
            System.out.println(" Receiver1 doing somethings.....");

            collectorClient.printer(receivedMessage, QQqueueStatus.UP.name());

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            System.out.println(" [x] Receiver1 error processing message: " + e.getMessage());
            boolean shouldRequeue = true;
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, shouldRequeue);
        }
    }
}
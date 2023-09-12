package com.cherry.producer.task;

import com.cherry.producer.constant.RabbitMqConstants;
import com.cherry.producer.enumeration.QExchangeStatus;
import com.cherry.producer.producer.QStatusManager;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
public class QExchangeReconnectTaskManager {

    private Timer timer;
    private QExchangeReconnectTask task;

    private final static long SEC_MILLS = 1000;

    @Autowired
    private QStatusManager qStatusManager;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private RabbitMqConstants rabbitMqConstants;

    public void start() {
        if (timer != null) {
            System.out.println("已有偵測排程執行中");
            return;
        }
        qStatusManager.setQueueStatus(QExchangeStatus.FAIL_OVER);

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
        qStatusManager.setQueueStatus(QExchangeStatus.UP);
    }

    public QExchangeStatus getQueueStatus() {
        return qStatusManager.getQueueStatus();
    }

    public boolean isRabbitUp() {
        return qStatusManager.isRabbitUp();
    }

}

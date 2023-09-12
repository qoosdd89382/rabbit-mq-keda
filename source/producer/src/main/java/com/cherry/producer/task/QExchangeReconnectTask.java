package com.cherry.producer.task;

import com.cherry.producer.enumeration.QExchangeStatus;

import java.util.TimerTask;

public class QExchangeReconnectTask extends TimerTask {

    QExchangeReconnectTaskManager qExchangeReconnectTaskManager;

    public QExchangeReconnectTask(QExchangeReconnectTaskManager qExchangeReconnectTaskManager) {
        this.qExchangeReconnectTaskManager = qExchangeReconnectTaskManager;
    }

    @Override
    public void run() {
        System.out.println("TASK START");
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

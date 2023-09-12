package com.cherry.producer.producer;

import com.cherry.producer.enumeration.QExchangeStatus;
import org.springframework.stereotype.Component;

@Component
public class QStatusHolder {

    private  QExchangeStatus qExchangeStatus = QExchangeStatus.FAIL_OVER;

    public QExchangeStatus getQueueStatus() {
        return qExchangeStatus;
    }

    public synchronized void setQueueStatus(QExchangeStatus QExchangeStatus) {
        this.qExchangeStatus = QExchangeStatus;
    }
}

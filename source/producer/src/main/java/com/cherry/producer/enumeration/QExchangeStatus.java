package com.cherry.producer.enumeration;

public enum QExchangeStatus {

    UP,
    FAIL_OVER;

    public boolean isFailingOver() {
        return this == QExchangeStatus.FAIL_OVER;
    }
}

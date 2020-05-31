package com.ego.entity;

public class Result {
    private String orderId;
    private long total;

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(final String orderId) {
        this.orderId = orderId;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(final long total) {
        this.total = total;
    }
}

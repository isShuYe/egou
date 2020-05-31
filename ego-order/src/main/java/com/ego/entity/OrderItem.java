package com.ego.entity;

import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;

import java.util.List;

public class OrderItem {
    private int paymentType;
    private String payment;
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;


    @Override
    public String toString() {
        return "OrderItem{" +
                "paymentType=" + paymentType +
                ", payment='" + payment + '\'' +
                ", orderItems=" + orderItems +
                ", orderShipping=" + orderShipping +
                '}';
    }

    public int getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(final int paymentType) {
        this.paymentType = paymentType;
    }

    public String getPayment() {
        return this.payment;
    }

    public void setPayment(final String payment) {
        this.payment = payment;
    }

    public List<TbOrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(final List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return this.orderShipping;
    }

    public void setOrderShipping(final TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}

package com.store.model;

public class PaymentOrder {

    private final String location;
    private final Double amount;
    private final Long orderNumber;

    public PaymentOrder(String location, Double amount, Long orderNumber){
        this.location = location;
        this.amount = amount;
        this.orderNumber = orderNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public String getLocation() {
        return location;
    }

}

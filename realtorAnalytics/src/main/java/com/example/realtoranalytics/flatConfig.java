package com.example.realtoranalytics;

public class flatConfig {
    String color;

    String address;

    Integer price;

    Integer payment;

    Long phone_number;

    String paymentType;

    public flatConfig(String color, String address, Integer price, Integer payment, Long phone_number, String paymentType){
        this.color=color;
        this.address = address;
        this.price = price;
        this.phone_number = phone_number;
        this.payment = payment;
        this.paymentType = paymentType;
    }

    public flatConfig(String address, Integer price, Integer payment, Long phone_number, String paymentType){
        this.address = address;
        this.price = price;
        this.phone_number = phone_number;
        this.payment = payment;
        this.paymentType = paymentType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Long phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}

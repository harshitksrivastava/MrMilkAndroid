package com.example.mrmilk.models;

public class Order {
    private String order_id;
    private String customer_id;
    private String order_status;
    private String order_date;
    private String delivery_date;
    private String order_address;
    private String total;
    private String transaction_id;
    private String cod;

    public Order(String customer_id, String order_status, String order_date, String delivery_date, String order_address, String total, String transaction_id, String cod) {
        this.customer_id = customer_id;
        this.order_status = order_status;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.order_address = order_address;
        this.total = total;
        this.transaction_id = transaction_id;
        this.cod = cod;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}

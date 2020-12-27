package com.example.sign.Model;

import java.util.List;

public class Request {

    private String address,total,status,email;
    private List<Order> orders;

    public Request() {
    }

    public Request(String email, String address, String total, List<Order> orders) {
        this.address = address;
        this.total = total;
        this.status = "0";
        this.email = email;
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}


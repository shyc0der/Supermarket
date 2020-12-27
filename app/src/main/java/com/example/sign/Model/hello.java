package com.example.sign.Model;

public class
hello {
    private String username;
    private String card;
    private String phone;
    private String receipt;
    private String email;


    public hello() {
    }

    public hello(String username, String card, String phone, String receipt, String email) {
        this.username = username;
        this.card = card;
        this.phone = phone;
        this.receipt = receipt;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
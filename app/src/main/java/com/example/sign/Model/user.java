package com.example.sign.Model;

public class user {
    String title,description,image,type,price,quantity;

    public user() {
    }

    public user(String title,String type,String description, String image, String price,String quantity) {

        this.title = title;
        this.type=type;
        this.description = description;
        this.image = image;
        this.price=price;
        this.quantity=quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

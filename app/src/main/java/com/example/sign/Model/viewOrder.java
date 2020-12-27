package com.example.sign.Model;

public class viewOrder {
    private String discount,image,productName,quantity,price;

    public viewOrder() {
    }

    public viewOrder(String discount, String image, String productName, String quantity, String price) {
        this.discount = discount;
        this.image = image;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;

    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}

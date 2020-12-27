package com.example.sign.Model;

public class Order {


   private String ProductId,Quantity,ProductName,Price,Discount,Image;

    public Order() {
    }

    public Order( String productId, String quantity, String productName, String price, String discount,String image) {

     ProductId = productId;
        Quantity = quantity;
        ProductName = productName;
        Price = price;
        Discount = discount;
        Image=image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}

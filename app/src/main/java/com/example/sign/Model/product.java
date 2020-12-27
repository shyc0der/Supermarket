package com.example.sign.Model;

public class product {

      private String name,description,image,price,discount,MenuId;


    public product() {
    }

    public product(String name, String description, String image, String price, String discount, String MenuId) {
       this.name=name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.discount =discount;
        this.MenuId=MenuId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


}



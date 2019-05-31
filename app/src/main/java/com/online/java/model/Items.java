package com.online.java.model;

public class Items {
    private String itemname, image, desc;
    private String itemprice;

    public Items(String itemname, String image, String desc, String price) {
        this.itemname = itemname;
        this.image = image;
        this.desc = desc;
        this.itemprice = price;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return itemprice;
    }

    public void setPrice(String price) {
        this.itemprice = price;
    }
}
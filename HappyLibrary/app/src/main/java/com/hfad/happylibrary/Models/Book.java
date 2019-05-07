package com.hfad.happylibrary;

public class Book {
    private int id;
    private String image;
    private String name;
    private String description;
    private String price;
    private String author;
    private String moreInfo;
    private String isavailable;

    public Book(int id, String name, String description, String price, String image, String author, String moreInfo, String isavailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.author = author;
        this.moreInfo = moreInfo;
        this.isavailable = isavailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getIsavailable() {
        return isavailable;
    }

    public void setIsavailable(String isavailable) {
        this.isavailable = isavailable;
    }
}

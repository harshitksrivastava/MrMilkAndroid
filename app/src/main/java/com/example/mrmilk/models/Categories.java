package com.example.mrmilk.models;

public class Categories {

    private String name;
    private String image_url;

    public Categories(String name, String image_url) {
        this.name = name;
        this.image_url = image_url;
    }

    public Categories() {

    }


    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

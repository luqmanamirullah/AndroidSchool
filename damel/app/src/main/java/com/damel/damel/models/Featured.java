package com.damel.damel.models;

public class Featured {
    public String imageUrl, title, desc;

    public Featured(String title, String desc, String imageUrl) {
       this.imageUrl = imageUrl;
       this.title = title;
       this.desc = desc;
    }
}

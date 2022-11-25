package com.damel.damel.models;

public class ForYou {
    public String name, people, imageUrl, city;
    public float rating;

    public ForYou(String name, String people, String imageUrl, String city, float rating) {
        this.name = name;
        this.people = people;
        this.imageUrl = imageUrl;
        this.city = city;
        this.rating = rating;
    }
}

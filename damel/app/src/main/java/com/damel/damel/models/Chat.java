package com.damel.damel.models;

public class Chat {
    public String name_person;
    public String message_current;
    public String time;
    public int image_profile;

    public Chat(String name_person, String message_current, String time, int image_profile) {
        this.name_person = name_person;
        this.message_current = message_current;
        this.time = time;
        this.image_profile = image_profile;
    }

    public String getName_person() {
        return name_person;
    }

    public String getMessage_current() {
        return message_current;
    }

    public String getTime() {
        return time;
    }

    public int getImage_profile() {
        return image_profile;
    }
}

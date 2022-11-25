package com.damel.damel.models;

public class Setting {
    public int icon;
    public String title, desc;

    public Setting(String title, String desc, int icon) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }
}

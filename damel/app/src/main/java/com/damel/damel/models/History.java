package com.damel.damel.models;

public class History {
    public int image_profile;
    public String name_worker;
    public String num_rate;
    public String name_job;
    public String pay;
    public String length_work;

    public History(int image_profile, String name_worker, String num_rate, String name_job, String pay, String length_work) {
        this.image_profile = image_profile;
        this.name_worker = name_worker;
        this.num_rate = num_rate;
        this.name_job = name_job;
        this.pay = pay;
        this.length_work = length_work;
    }
}

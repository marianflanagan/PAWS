package com.example.marianflanagan.paws.model;

/**
 * Created by marianflanagan on 30/07/2017.
 */

public class Bookings {

    String dogName;
    String customerId;
    String sitterId;
    String date;
    String time;


    public Bookings(String dogName, String customerId, String sitterId, String date, String time) {

        this.dogName = dogName;
        this.customerId = customerId;
        this.sitterId = sitterId;
        this.date = date;
        this.time = time;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSitterId() {
        return sitterId;
    }

    public void setSitterId(String sitterId) {
        this.sitterId = sitterId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

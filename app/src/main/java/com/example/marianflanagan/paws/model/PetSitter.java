package com.example.marianflanagan.paws.model;

/**
 * Created by marianflanagan on 27/07/2017.
 */

public class PetSitter {
    String name;
    String phone;
    String address;

    public PetSitter() {
    }

    public PetSitter(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

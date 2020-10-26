package com.example.mrmilk.models;

public class User {
    private String phone;
    private String name;
    private String email;
    private String address;
    private String is_superuser;


    public User(String phone, String name, String email, String address, String is_superuser) {
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.address = address;
        this.is_superuser = is_superuser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(String is_superuser) {
        this.is_superuser = is_superuser;
    }
}

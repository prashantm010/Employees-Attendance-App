package com.example.shubham.pnbapp;

/**
 * Created by prashant maheshwari on 24-07-2018.
 */

public class CreateUserModel {
    private String name, id1, pin,Gender, post, contactno, Dob, address, BG;

    public CreateUserModel() {
    }

    public CreateUserModel(String name, String id1, String pin, String gender, String post, String contactno, String dob, String address, String BG) {
        this.name = name;
        this.id1 = id1;
        this.pin = pin;
        this.Gender = gender;
        this.post = post;
        this.contactno = contactno;
        Dob = dob;
        this.address = address;
        this.BG = BG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBG() {
        return BG;
    }

    public void setBG(String BG) {
        this.BG = BG;
    }
}
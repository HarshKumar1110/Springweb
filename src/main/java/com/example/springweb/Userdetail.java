package com.example.springweb;

import javax.persistence.*;

@Entity
@Table(name = "userdetails")
public class Userdetail {
    @Id
    @Column(name = "username", nullable = false, length = 30)
    private String username;

    @Column(name = "fname", length = 20)
    private String fname;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "phone", length = 10)
    private String phone;

    @Column(name = "lname", length = 20)
    private String lname;

    @Column(name = "type", length = 10)
    private String type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
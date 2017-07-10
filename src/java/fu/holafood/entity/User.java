/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author hoadx
 */
public class User {
 
    private int id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String permi;
    private int gender;
    private Date dob;
    private Timestamp createdAt;

    public User() {
    }

    public User(int id, String username, String password, String email, String fullname, String permi, int gender, Date dob, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.permi = permi;
        this.gender = gender;
        this.dob = dob;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPermi() {
        return permi;
    }

    public void setPermi(String permi) {
        this.permi = permi;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return id + ": " + username + " - " + fullname;
    }
    
}

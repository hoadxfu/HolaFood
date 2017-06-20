/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.entity;

import java.sql.Date;

/**
 *
 * @author hoadx
 */
public class User {
 
    private int id;
    private String username;
    private String password;
    private String fullname;
    private int permi;
    private int gender;
    private Date dob;
    private Date createdAt;

    public User() {
    }

    public User(int id, String username, String password, String fullname, int permi, int gender, Date dob, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getPermi() {
        return permi;
    }

    public void setPermi(int permi) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return id + ": " + username + " - " + fullname;
    }
    
}

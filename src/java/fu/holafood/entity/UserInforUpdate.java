/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

import java.sql.Date;

/**
 *
 * @author NhocNho
 * This Class is just to update information
 */
public class UserInforUpdate {
    private int id;
    private String password;
    private String email;
    private String fullname;
    private String gender;
    private Date dob;

    public UserInforUpdate() {
    }

    public UserInforUpdate(int id, String password, String email, String fullname, String gender, Date dob) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.gender = gender;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    
}

/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Ray Sparrow
 */
public class Category {
    int id;
    int pid;
    String slug;
    String name;
    String descreption;
    Timestamp createdAt;
    Timestamp updatedAt;

    public Category() {
    }

    public Category(int id, int pid, String slug, String name,String descreption, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.pid = pid;
        this.slug = slug;
        this.name = name;
        this.descreption = descreption;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}

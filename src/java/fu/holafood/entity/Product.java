/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author NhocNho
 */
public class Product {

    private int id;
    private String name;
    private String slug;
    private String description;
    private String imgFeature;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Product() {
    }

    public Product(int id, String name, String slug, String description, String imgFeature, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.imgFeature = imgFeature;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgFeature() {
        return imgFeature;
    }

    public void setImgFeature(String imgFeature) {
        this.imgFeature = imgFeature;
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

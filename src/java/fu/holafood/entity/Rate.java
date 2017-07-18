/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

import java.sql.Timestamp;

/**
 *
 * @author Ray Sparrow
 */
public class Rate {
    private int id;
    private int item_id;
    private int value;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Rate() {
    }

    public Rate(int id, int item_id, int value, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.item_id = item_id;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

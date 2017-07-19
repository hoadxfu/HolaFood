/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;


import java.sql.Timestamp;

/**
 *
 * @author BH_Wind
 */
public class Reviews {
    int id;
    int item_id;
    int userId;
    String value;
    Timestamp cread_at;
    Timestamp updated_at;

    public Reviews() {
    }

    public Reviews(int id, int item_id, int userId, String value, Timestamp cread_at, Timestamp updated_at) {
        this.id = id;
        this.item_id = item_id;
        this.userId = userId;
        this.value = value;
        this.cread_at = cread_at;
        this.updated_at = updated_at;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Timestamp getCread_at() {
        return cread_at;
    }

    public void setCread_at(Timestamp cread_at) {
        this.cread_at = cread_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
 
}

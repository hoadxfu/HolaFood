/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

/**
 *
 * @author hoadx
 */
public class Review {

    private int id;
    private int itemId;
    private int userId;
    private String userName;
    private String value;

    public Review() {
    }

    public Review(int id, int itemId, int userId, String userName, String value) {
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.userName = userName;
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

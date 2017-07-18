/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.entity;

import java.sql.Timestamp;

/**
 *
 * @author NhocNho
 */
public class ProductCategory {
    private int productId;
    private int categoryId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ProductCategory() {
    }

    public ProductCategory(int productId, int categoryId, Timestamp createdAt, Timestamp updatedAt) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.action;

import com.opensymphony.xwork2.ActionSupport;
import fu.holafood.entity.Product;
import fu.holafood.model.ProductModel;
import javassist.SerialVersionUID;

/**
 *
 * @author hoadx
 */
public class ProductDetailAction extends ActionSupport {

    private int id;
    private String slug;
    private Product product;

    @Override
    public String execute() throws Exception {
        ProductModel pm = new ProductModel();
        product = pm.getById(id);
        return SUCCESS;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}

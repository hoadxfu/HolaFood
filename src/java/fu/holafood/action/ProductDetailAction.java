/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.action;

import com.opensymphony.xwork2.ActionSupport;
import fu.holafood.entity.Product;
import fu.holafood.model.ProductModel;
import java.util.ArrayList;
import javassist.SerialVersionUID;

/**
 *
 * @author hoadx
 */
public class ProductDetailAction extends ActionSupport {

    private int id;
    private String slug;
    private Product product;
    private ArrayList<Product> relatedProduct;

    @Override
    public String execute() throws Exception {
        ProductModel pm = new ProductModel();
        product = pm.getById(id);
        relatedProduct = pm.getRelated(product);
        System.out.println(relatedProduct.size());
        return SUCCESS;
    }

    public ArrayList<Product> getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(ArrayList<Product> relatedProduct) {
        this.relatedProduct = relatedProduct;
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

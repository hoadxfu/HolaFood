/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.action;

import com.opensymphony.xwork2.ActionSupport;
import fu.holafood.entity.Product;
import fu.holafood.model.ProductModel;
import java.util.ArrayList;

/**
 *
 * @author hoadx
 */
public class HomeAction extends ActionSupport {

    private ArrayList<Product> products;

    @Override
    public String execute() throws Exception {
        ProductModel pm = new ProductModel();
        products = pm.getAll();
        return SUCCESS;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}

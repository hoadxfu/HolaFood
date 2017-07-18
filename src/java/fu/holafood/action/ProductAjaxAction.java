/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.action;

import com.opensymphony.xwork2.ActionSupport;
import fu.holafood.entity.Product;
import fu.holafood.model.ProductModel;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author hoadx
 */
public class ProductAjaxAction extends ActionSupport {

    private ArrayList<Product> products;

    public ProductAjaxAction() throws Exception {
        ProductModel pm = new ProductModel();
        products = pm.getAll();
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}

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

    final int postPerPage = 6;
    private int paged;
    private String filter;
    private ArrayList<Product> products;

    @Override
    public String execute() throws Exception {
        ProductModel pm = new ProductModel();
        int limit = paged * postPerPage;
        int offset = (paged - 1) * postPerPage;
        products = pm.getAll(limit, offset, filter);
        return SUCCESS;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getPaged() {
        return paged;
    }

    public void setPaged(int paged) {
        this.paged = paged;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}

/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.action;

import com.opensymphony.xwork2.ActionSupport;
import fu.holafood.entity.Category;
import fu.holafood.entity.Product;
import fu.holafood.entity.Review;
import fu.holafood.entity.User;
import fu.holafood.model.ProductModel;
import fu.holafood.model.UserModel;
import java.util.ArrayList;
import javassist.SerialVersionUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

/**
 *
 * @author hoadx
 */
public class ProductDetailAction
        extends ActionSupport
        implements ServletResponseAware, ServletRequestAware {

    private int id;
    private String slug;
    private String loggedUser;
    private Product product;
    private ArrayList<Category> categories;
    private ArrayList<Product> relatedProduct;

    // form review
    private String mess;
    private String review;
    private ArrayList<Review> listReview;

    @Override
    public String execute() throws Exception {

        // get user from cookie
        UserModel um = new UserModel();
        // Load from cookie
        for (Cookie c : servletRequest.getCookies()) {
            if (c.getName().equals("username")) {
                loggedUser = c.getValue();
            }
        }

        ProductModel pm = new ProductModel();
        product = pm.getById(id);
        if (product == null) {
            return ERROR;
        }
        categories = pm.getCategoriesOfProduct(id);
        listReview = pm.getAllReviewOfProduct(id);
        relatedProduct = new ArrayList<>();
        for (Category c : categories) {
            relatedProduct.addAll(pm.getRelatedProducts(id, c.getId()));
        }

        if (loggedUser != null && loggedUser != "") {
            User user = um.getUserIdByName(loggedUser);
            if (review != null && review != "") {
                boolean success = pm.addReview(id, user.getId(), review);
                if (success) {
                    mess = "reviewSuccess";
                    return "reviewSuccess";
                } else {
                    mess = "reviewError";
                    return "reviewError";
                }
            }
        }
        return SUCCESS;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public ArrayList<Review> getListReview() {
        return listReview;
    }

    public void setListReview(ArrayList<Review> listReview) {
        this.listReview = listReview;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
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

    protected HttpServletResponse servletResponse;

    @Override
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    protected HttpServletRequest servletRequest;

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

}

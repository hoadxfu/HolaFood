/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Category;
import fu.holafood.entity.Product;
import fu.holafood.entity.Review;
import fu.holafood.entity.User;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hoadx
 */
public class ProductModel extends DBContext {

    public ProductModel() {
        super();
    }

    /**
     * get all product
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Product> getAll(int limit, int offset, String filter) throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "{call fetchProduct(?, ?, ?)}";
        CallableStatement cs = getConnection().prepareCall(sql);
        cs.setInt(1, limit);
        cs.setInt(2, offset);
        if (filter == null) {
            filter = "";
        }
        cs.setString(3, '%' + filter + '%');
        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("slug"),
                    rs.getString("description"),
                    rs.getString("img_feature"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            ));
        }
        return list;
    }

    /**
     * get all product
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Product> getAll() throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products order by id desc";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("slug"),
                    rs.getString("description"),
                    rs.getString("img_feature"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            ));
        }
        return list;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Product getById(int id) throws Exception {
        Product p = null;
        String sql = "select * from products where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            p = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("slug"),
                    rs.getString("description"),
                    rs.getString("img_feature"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            );
        }
        return p;
    }

    public boolean addReview(int id, int uid, String content) throws Exception {
        String sql = "insert into reviews (item_id, user_id, value) values (?, ?, ?)";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, id);
        ps.setInt(2, uid);
        ps.setString(3, content);

        return ps.executeUpdate() > 0;
    }

    public ArrayList<Review> getAllReviewOfProduct(int pid) throws Exception {
        ArrayList<Review> list = new ArrayList<>();
        String sql = "select r.*, u.username from reviews as r "
                + "inner join users as u "
                + "on r.user_id = u.id "
                + "where item_id = ? order by id desc";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, pid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Review(
                    rs.getInt("id"),
                    rs.getInt("item_id"),
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("value")
            ));
        }
        return list;
    }

    public ArrayList<Category> getCategoriesOfProduct(int pid) throws Exception {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "select * from categories as c \n"
                + "inner join product_category as pc \n"
                + "on c.id = pc.category_id \n"
                + "where pc.product_id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, pid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Category(
                    rs.getInt("id"),
                    rs.getInt("pid"),
                    rs.getString("slug"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            ));
        }
        return list;
    }

    /**
     *
     * @param pid
     * @param cid
     * @return
     * @throws Exception
     */
    public ArrayList<Product> getRelatedProducts(int pid, int cid) throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select top 5 p.* from products as p \n"
                + "inner join product_category as pc \n"
                + "on pc.product_id = p.id \n"
                + "where pc.category_id = ? and p.id != ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, cid);
        ps.setInt(2, pid);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("slug"),
                    rs.getString("description"),
                    rs.getString("img_feature"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            ));
        }
        return list;
    }

}

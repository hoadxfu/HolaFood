/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Product;
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
    public ArrayList<Product> getAll(int limit, int offset) throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "{call fetchProduct(?, ?)}";
        System.out.println(sql);
        CallableStatement cs = getConnection().prepareCall(sql);
        cs.setInt(1, limit);
        cs.setInt(2, offset);
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
     * @return
     * @throws Exception 
     */
    public ArrayList<Product> getRelated(Product product) throws Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "select * from products where name like ? order by id desc";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, '%' + product.getName() + '%');
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

}

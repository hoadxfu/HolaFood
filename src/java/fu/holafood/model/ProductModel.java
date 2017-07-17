/*
 * HoladFood Web Application
 * @author: Team 3 - SWE
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Product;
import fu.holafood.entity.User;
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
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            ));
        }
        return list;
    }
    
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
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("updated_at")
            );
        }
        return p;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.Category;
import fu.holafood.entity.PermissionGroup;
import fu.holafood.entity.Product;
import fu.holafood.entity.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.Cookie;

/**
 *
 * @author hoadx
 */
public class UserModel extends DBContext {

    public UserModel() {
        super();
    }

    public ArrayList<User> getUsers() throws Exception {
        ArrayList<User> users = new ArrayList<>();
        String sql = "select * from users u inner join permission_groups permi_gr on u.permi = permi_gr.id";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            users.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(11),
                    rs.getInt(7) == 1 ? "Male" : "Female",
                    rs.getDate(8),
                    rs.getTimestamp(9)
            ));
        }
        return users;
    }

    public ArrayList<Product> getProducts() throws Exception {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "select * from products";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            products.add(new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getTimestamp(7)));
        }
        return products;
    }

    public int addUsers(String userName, String password, String email, String fullName, int permi, int gender, Date dob, Timestamp created_at) throws Exception {

        String insert = "INSERT INTO Users(username,password,email,fullname,permi,gender,dob,created_at) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = getConnection().prepareCall(insert);
        ps.setString(1, userName);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setString(4, fullName);
        ps.setInt(5, permi);
        ps.setInt(6, gender);
        ps.setDate(7, dob);
        ps.setTimestamp(8, created_at);

        int result = ps.executeUpdate();
        return result;
    }

    public boolean login(String username, String password) {
        try {
            String sql = "select * from Users where username=? and password=?";
            PreparedStatement ps = getConnection().prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("can not connect to database. " + e);
            return false;
        }
        return false;
    }

    public String getPermision(Cookie c) throws Exception {
        if (c != null) {
            String userName = c.getValue();
            String sql = "select * from users u inner join permission_groups pemigr on u.permi = pemigr.id where username = ?";
            PreparedStatement ps = getConnection().prepareCall(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        }
        return "";
    }
    
    public int getUserIdByName(String name) throws Exception {
        String sql = "select * from users where username = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt("id");
        }
        return 0;
    }

    public ArrayList<PermissionGroup> getPermissionGroups() throws Exception {
        ArrayList<PermissionGroup> permissionGroups = new ArrayList<>();
        String sql = "select * from permission_groups";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            permissionGroups.add(new PermissionGroup(
                    rs.getInt(1),
                    rs.getString(2)
            ));
        }
        return permissionGroups;
    }

    public int addProduct(String name, String slug, String des, String imgFeature, Timestamp createAt, Timestamp updateAt) throws Exception {
        String sql = "insert into products(name, slug, description, img_feature, created_at, updated_at) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, name);
        ps.setString(2, slug);
        ps.setString(3, des);
        ps.setString(4, imgFeature);
        ps.setTimestamp(5, createAt);
        ps.setTimestamp(6, updateAt);
        return ps.executeUpdate();
    }

    public User getUserById(int userId) throws Exception {
        String sql = "select * from users u inner join permission_groups permi_gr on u.permi = permi_gr.id where u.id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new User(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(11),
                    rs.getInt(7) == 1 ? "Male" : "Female",
                    rs.getDate(8),
                    rs.getTimestamp(9)
            );
        }
        return null;
    }

    public ArrayList<Category> getCategory() throws Exception {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "select * from categories";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            categories.add(new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7)));
        }
        return categories;
    }

    public int addCategory(int pid, String slug, String name, String des, Timestamp createAt, Timestamp updateAt) throws Exception {

        String sql = "insert into categories(pid, slug, name, description, created_at, updated_at) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, pid);
        ps.setString(2, slug);
        ps.setString(3, name);
        ps.setString(4, des);
        ps.setTimestamp(5, createAt);
        ps.setTimestamp(6, updateAt);
        return ps.executeUpdate();

    }
    
    public int addProductCategory(int productId, int categoryId, Timestamp createAt, Timestamp updateAt ) throws Exception{
        String sql = "insert into product_category values(?, ?, ?, ?)";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, productId);
        ps.setInt(2, categoryId);
        ps.setTimestamp(3, createAt);
        ps.setTimestamp(4, updateAt);
        return ps.executeUpdate();
    }

    public int deleteUser(int userId) throws Exception {
        String sql = "delete from users where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, userId);
        return ps.executeUpdate();
    }

    public int updateUser(int userId, String newPassword, String newEmail, String newFullName, int newGender, Date newDob) throws Exception {
        String sql = "update Users set password = ?, email = ?, fullname = ?, gender = ?, dob = ? where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, newPassword);
        ps.setString(2, newEmail);
        ps.setString(3, newFullName);
        ps.setInt(4, newGender);
        ps.setDate(5, newDob);
        ps.setInt(6, userId);
        return ps.executeUpdate();
    }

    public int deleteProduct(int productId) throws Exception {
        String sql = "delete from products where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, productId);
        return ps.executeUpdate();
    }

    public int deleteProductCategory(int id) throws Exception {
        String sql = "delete from product_category where product_id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, id);
        return ps.executeUpdate();
    }

    public int deleteCategory(int categoryId) throws Exception {
        String sql = "delete from categories where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, categoryId);
        return ps.executeUpdate();
    }

    public Product getProductById(int productId) throws Exception {
        String sql = "select * from products p where p.id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new Product(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getTimestamp(7));
        }
        return null;
    }

    public Category getCategoryById(int categoryId) throws Exception {
        String sql = "select * from categories p where p.id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return new Category(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7));
        }
        return null;
    }

    public int updateProduct(int id, String name, String slug, String des, Timestamp createAt, Timestamp updateAt) throws Exception {
        String sql = "update Products set name = ?, slug = ?, description = ?, created_at = ?, updated_at = ? where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, name);
        ps.setString(2, slug);
        ps.setString(3, des);
        ps.setTimestamp(4, createAt);
        ps.setTimestamp(5, updateAt);
        ps.setInt(6, id);
        return ps.executeUpdate();
    }

    public int updateCategory(int id, int p_id, String name, String slug, String des, Timestamp createAt, Timestamp updateAt) throws Exception {
        String sql = "update categories set pid = ?, name = ?, slug = ?, description = ?, created_at = ?, updated_at = ? where id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, p_id);
        ps.setString(2, name);
        ps.setString(3, slug);
        ps.setString(4, des);
        ps.setTimestamp(5, createAt);
        ps.setTimestamp(6, updateAt);
        ps.setInt(7, id);
        return ps.executeUpdate();
    }

    public int getMaxId(String tableName) throws Exception {
        int maxID = 0;
        Statement s2 = getConnection().createStatement();
        s2.execute("SELECT MAX(id) FROM " + tableName);
        ResultSet rs2 = s2.getResultSet(); // 
        while (rs2.next()) {
            maxID = rs2.getInt(1);
        }
        return maxID;
    }
    
    public ArrayList<Integer> getListCategoriesByProductId(int productId) throws Exception {
        ArrayList<Integer> categoriesId = new ArrayList<>();
        String sql = "Select * from product_category where product_id = ?";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            categoriesId.add(rs.getInt(2));
        }
        return categoriesId;
    }
 
}

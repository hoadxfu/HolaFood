/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.PermissionGroup;
import fu.holafood.entity.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
                    rs.getInt(7),
                    rs.getDate(8),
                    rs.getTimestamp(9)
            ));
        }
        return users;
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
    
    public int AddProduct(String name, String slug, String des, Timestamp createAt, Timestamp updateAt) throws Exception {
        String sql = "insert into products(name, slug, description, created_at, updated_at) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ps.setString(1, name);
        ps.setString(2, slug);
        ps.setString(3, des);
        ps.setTimestamp(4, createAt);
        ps.setTimestamp(5, updateAt);
        return ps.executeUpdate();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        String sql = "select * from users";
        PreparedStatement ps = getConnection().prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            users.add(new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getDate(7),
                    rs.getDate(8)
            ));
        }
        return users;
    }

    public void addUsers(String userName, String password, String email, String fullName, int permi, int gender, Date dob, Date created_at) throws Exception {

        String insert = "INSERT INTO Users(username,password,email,fullname,permi,gender,dob,created_at) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = getConnection().prepareCall(insert);
        ps.setString(1, userName);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.setString(4, fullName);
        ps.setInt(5, permi);
        ps.setInt(6, gender);
        ps.setDate(7, dob);
         ps.setDate(8, created_at);

        int result = ps.executeUpdate();
    }

}

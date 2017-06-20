/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.model;

import fu.holafood.db.DBContext;
import fu.holafood.entity.User;
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.holafood.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hoadx
 */
public class DBContext {

    //return Connection object
    public Connection getConnection() throws Exception {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;"
                + "database=holafood_db;"
                + "user=sa;"
                + "password=abc123@!";
        //loading sql driver
        Class.forName(driver);
        //open and return connection
        return DriverManager.getConnection(url);
    }
}

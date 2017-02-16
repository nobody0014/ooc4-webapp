package io.muic.ooc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by wit on 2/15/2017 AD.
 */
public class DatabaseConnectionService {
    public static Connection getConnection()
    {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost/ooc4-webapp?user=wit&password=helloWorld0014()";
            Class.forName("com.mysql.jdbc.Driver");
            try
            {
                con = DriverManager.getConnection(url);
            }

            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        } catch(ClassNotFoundException e) {
            System.out.println(e);
        }
        return con;
    }
}

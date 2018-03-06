/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    
    private static final String IP          = "46.101.114.190";
    private static final String PORT        = "3306";
    private static final String DATABASE    = "monday";
    private static final String USERNAME    = "root";
    private static final String PASSWORD    = "password123";
    
    private static final String URL = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;

    public static Connection connection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
            return connection;
    }
}
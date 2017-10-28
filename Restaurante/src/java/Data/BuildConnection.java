/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class BuildConnection {
    
    public static Connection pg() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Driver driver = (Driver)Class.forName("org.postgresql.Driver").newInstance();
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Restaurante", "postgres", "postgres");
        return connection;
    }
    
}

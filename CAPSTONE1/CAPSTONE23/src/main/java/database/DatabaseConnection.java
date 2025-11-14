/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aliya
 */
public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/greensamarinda";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() {} // private constructor (Singleton)

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
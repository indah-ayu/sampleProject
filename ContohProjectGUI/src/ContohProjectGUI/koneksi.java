/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ContohProjectGUI;

import java.sql.*;

/**
 *
 * @author FACULTY
 */
public class koneksi 
{
    private static String url = "jdbc:sqlserver://FACULTY-PC\\SQL_AYU;databaseName=Perkuliahan;";
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String username = "sa";
    private static String password = "123456";
    private static Connection con;

    public static Connection getConnection() 
    {
        try 
        {
            Class.forName(driverName);
            try 
            {
                con = DriverManager.getConnection(url, username, password);
            } 
            catch (SQLException ex) 
            {
                System.out.println("Failed to create the database connection.");
            }
        } 
        catch (ClassNotFoundException ex) 
        {
            System.out.println("Driver not found.");
        }
        return con;
    }
}

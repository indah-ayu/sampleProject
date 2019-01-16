/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Baru;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import ContohProjectGUI.koneksi;

/**
 *
 * @author FACULTY
 */
public class belajardb4 {
    public static void main(String[] args) 
    {
	Integer i;
        Integer total;	
	Connection con = null;
	try 
        {
		con = koneksi.getConnection();
	} 
        catch (Exception e) 
        {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
	}

	if (con != null) 
        {
	    System.out.println("You made it, take control your database now!");
	} 
        else 
        {
            System.out.println("Failed to make connection!");
	}
	Statement stmt;
	try 
        {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql;
            String ip;
            String ipbefore;
            Integer p;
            Integer itungsesi;
            //String[] ipstorage;
            sql = "SELECT * FROM hasilpraproses1urut ";
            ResultSet rs = stmt.executeQuery(sql);
            i=0;
            itungsesi=0;
            ipbefore="";
            while (rs.next()) 
            {
		ip= rs.getString(2);
		if (ipbefore.equals(ip))
		{
                    itungsesi++;
		} 
                else
		{
                    ipbefore=ip;
                    itungsesi=0;
		};
		System.out.println("ip :");
		System.out.println(ip);
		System.out.println(itungsesi);
		i++;
            } 
            //System.out.println("ip:");
            //System.out.println(ip);
				
			total=i;
			System.out.println("total:");
			System.out.println(total);

			rs.close();
			stmt.close();
		
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

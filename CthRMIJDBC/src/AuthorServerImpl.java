//AuthorServerImpl.java
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
public class AuthorServerImpl extends UnicastRemoteObject implements AuthorServer
{
	static ResultSet result;
	static Connection con;
	static PreparedStatement stat;
	
	/* Define the default constructor of the Impl class */
	public AuthorServerImpl() throws RemoteException
	{
		super();
	}
	
	/* Define the remote method */
	public String insertDetails(String authorID, String lastName, String firstName, String phone, String address, String city, String state, String zip) throws RemoteException
	{

		int rowsAffected = 0;
		String sReturn = "fail";
		try
		{
			 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection con=DriverManager.getConnection("jdbc:sqlserver://FACULTY-PC\\SQL_AYU:1433;databaseName=J2EE1_DEMO" ,"sa","123456");

			/* Create the prepareStatement to insert the value in the database. */

			stat=con.prepareStatement("insert into authors values (?, ?, ?, ?, ?,?,?,?)");
			stat.setString(1, authorID);

			stat.setString(2, lastName);

			stat.setString(3, firstName);

			stat.setString(4, phone);

			stat.setString(5, address);

			stat.setString(6, city);

			stat.setString(7, state);

			stat.setString(8, zip);

			rowsAffected = stat.executeUpdate();
			if(rowsAffected>0)
			{
				sReturn = "success";
			}
		}
		catch(Exception v)
		{
			System.out.println("Error at value insert" + v);
		}
		return sReturn;
	}
	
	/* Define the main() method */
	public static void main(String args[])
	{
		/* Set the security manager. */
		System.setSecurityManager(new RMISecurityManager());
		try
		{
			/* Create the instance of the Impl class. */
			AuthorServerImpl instance = new AuthorServerImpl();
			/* Bind the server object to the RMI registry. */
			Naming.rebind("AuthorServer", instance);
			System.out.println("Server Registered");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}

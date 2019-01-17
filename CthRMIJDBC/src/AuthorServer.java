//AuthorServer.java
import java.rmi.*;   
public interface AuthorServer extends Remote
{
	/* Declare the remote method. */
	String insertDetails(String authorID, String lastName, String firstName, String phone, String address, String city, String state, String zip) throws RemoteException;	
}

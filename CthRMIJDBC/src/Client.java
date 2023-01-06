//Client.java
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;
import java.awt.*;
public class Client
{
	/* Declare the variables */
	static JFrame frame;
	static JPanel panel;
	static JPanel panel1;
	
	JLabel labelAuthorID;
	JLabel labelLastName;
	JLabel labelFirstName;
	JLabel labelPhone;
	JLabel labelAddress;
	JLabel labelCity;
	JLabel labelState;
	JLabel labelZip;
	
	JTextField textAuthorID;
	JTextField textLastName;
	JTextField textFirstName;
	JTextField textPhone;
	JTextField textAddress;
	JTextField textCity;
	JTextField textState;
	JTextField textZip;
	
	JButton submit;
	
	static String authorID;
	static String lastName;
	static String firstName;
	static String phone;
	static String address;
	static String city;
	static String state;
	static String zip;
	
	/* Define the default constructor */
	public Client()
	{
		/* Create the JFrame */
		frame=new JFrame("Earnest Publishing House");
		panel = new JPanel();
		panel1 = new JPanel();
		/* Set the Layout managers */
		panel.setLayout(new GridLayout(8,2));
		panel1.setLayout(new GridLayout(1,1));
		frame.setVisible(true);
		frame.setSize(400, 400);
		frame.getContentPane().setLayout(new BorderLayout());
		
		/* Define the swing components on the JFrame */	
		labelAuthorID = new JLabel("Author ID");
		labelLastName = new JLabel("Last Name");
		labelFirstName = new JLabel("First Name");
		labelAddress = new JLabel("Phone");
		labelPhone = new JLabel("Address");
		labelCity = new JLabel("City");
		labelState = new JLabel("State");
		labelZip = new JLabel("Zip");
		
		textAuthorID = new JTextField(5);
		textLastName = new JTextField(15);
		textFirstName = new JTextField(15);
		textPhone = new JTextField(10);
		textAddress = new JTextField(50);
		textCity = new JTextField(10);
		textState = new JTextField(10);
		textZip = new JTextField(6);
		
		submit = new JButton("Submit");
				
		/* Add the swing components on the panel */	
		panel.add(labelAuthorID);
		panel.add(textAuthorID);
		panel.add(labelLastName);
		panel.add(textLastName);
		panel.add(labelFirstName);
		panel.add(textFirstName);
		panel.add(labelPhone);
		panel.add(textPhone);
		panel.add(labelAddress);
		panel.add(textAddress);
		panel.add(labelCity);
		panel.add(textCity);
		panel.add(labelState);
		panel.add(textState);
		panel.add(labelZip);
		panel.add(textZip);
		panel1.add(submit);
		ButtonListener blisten = new ButtonListener();
		submit.addActionListener(blisten);
		frame.getContentPane().add(new JPanel(), BorderLayout.WEST);
		frame.getContentPane().add(new JPanel(), BorderLayout.EAST);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(panel1, BorderLayout.SOUTH);
	}
	/* Create a ButtonListener class */
	class ButtonListener implements ActionListener
	{
		/* Define the actionPerformed() method */
		public void actionPerformed(ActionEvent evt)
		{
			JButton source=(JButton)evt.getSource();
			MyDialog myDialog;
			try
			{
				/* Lookup the server objcet */
				AuthorServer server = (AuthorServer)Naming.lookup("rmi://127.0.0.1/AuthorServer");
				
				/* Retreive the details of an author from the Clint frame */
				authorID=textAuthorID.getText();
				lastName=textLastName.getText();
				firstName=textFirstName.getText();
				phone=textPhone.getText();
				address=textAddress.getText();
				city=textCity.getText();
				state=textState.getText();
				zip=textZip.getText();
				
				/* Invoke the remote method */
				String str=server.insertDetails(authorID, lastName, firstName, phone, address, city, state, zip);
				System.out.println(str);
				if(str.equals("success"))
				{
					/* Create the object of MyDialog class */
					myDialog = new MyDialog(frame, "Inserted Successfully");
				}
				else
				{
					/* Create the object of MyDialog class */
					myDialog = new  MyDialog(frame, "No Record Inserted");
				}
				myDialog.setVisible(true);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}
	
	/* Define the main() method */
	public static void main(String args[])
	{
		/* Create the object of the Client class */
		new Client();
	}
}

/* Create the MyDialog class */
class MyDialog extends Dialog implements ActionListener
{
	/* Define the default constructor of the MyDialog class */
	MyDialog(Frame parent, String title)
	{
		super(parent, title, false);
		setLayout(new FlowLayout());
		setSize(200, 80);
		add(new JLabel (title));
		JButton btn_OK  = new JButton("OK");
		add(btn_OK);
		btn_OK.addActionListener(this);
	}
	/* Define the actionPerformed() method */
	public void actionPerformed(ActionEvent ae)
	{
		dispose();
	}
}

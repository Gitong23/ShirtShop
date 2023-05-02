package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import com.mysql.jdbc.PreparedStatement;

import Common.GlobalData;

public class CustomerManager {
	public static boolean checkLoging(String user_id, String user_password) {
		try
	    {
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM customers WHERE user_id = '"+user_id+"' AND user_password = '"+ user_password +"'  ";
	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  GlobalData.CurrentUser_id = rs.getString(2);
	    	  GlobalData.CurrentUser_password = rs.getString(3);
	    	  GlobalData.CurrentUser_usertype = rs.getString(4);
	    	  return true;
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
		return false; //wrong id password
	}
	
	public static CustomerDB getCustomer() {
		
		CustomerDB customerDB = new CustomerDB();
		
		try
	    {
	      // create our mysql database connection
	      String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
	      
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT * FROM customers WHERE user_id = '"+GlobalData.CurrentUser_id+"' AND user_password = '"+ GlobalData.CurrentUser_password +"'  ";
	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	    	  customerDB.customer_id = rs.getInt(1);
	    	  customerDB.user_id = rs.getString(2);
	    	  customerDB.user_password = rs.getString(3);
	    	  customerDB.user_type = rs.getString(4);
	    	  customerDB.user_name = rs.getString(5);
	    	  customerDB.user_surname = rs.getString(6);
	    	  customerDB.user_phone = rs.getString(7);
	    	  customerDB.address = rs.getString(8);
	    	  customerDB.kat = rs.getString(9);
	    	  customerDB.kwang = rs.getString(10);
	    	  customerDB.provice = rs.getString(11);
	    	  customerDB.zipCode = rs.getString(12);
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	      
		return customerDB;
	}
	
	public static boolean isHasBeenUsedId (String x) {
		ArrayList<String> list_id = new ArrayList<String>();
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
		    String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
		    
		    String query = "SELECT * FROM customers";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	String user_id = rs.getString(2);
		    	list_id.add(user_id);
		    }
		    st.close();
		    
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		
		int foundStatus = Collections.binarySearch(list_id, x);
		
		if(foundStatus < 0 )return false;
			
		return true;
	}
	
	public static void saveNewCustomer(CustomerDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			
			String query = "INSERT INTO customers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			st.setInt(1, 0);
			st.setString(2, x.user_id);
			st.setString(3, x.user_password);
			st.setString(4, "user");
			st.setString(5, x.user_name);
			st.setString(6, x.user_surname);
			st.setString(7, x.user_phone);
			st.setString(8, x.address);
			st.setString(9, x.kat);
			st.setString(10, x.kwang);
			st.setString(11, x.provice);
			st.setString(12, x.zipCode);

			st.executeUpdate();	      
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void editCustomer(CustomerDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			
			String query = "UPDATE `customers` SET `customer_id`= "+x.customer_id+ ",`user_id`= '"+x.user_id+"' ,`user_password`= '"+x.user_password+"' ,`user_type`= '"+x.user_type
					+ "',`name`= '"+x.user_name+"' ,`surname`= '"+x.user_surname+"' ,`phone`= '"+x.user_phone+"' ,`address`= '"+x.address+"' ,`kat`= '"+x.kat
					+ "', `kwang`= '"+x.kwang+"' ,`provice`= '"+x.provice+"' ,`zipCode`= '"+x.zipCode+"' WHERE `customer_id` = "+x.customer_id+";";
			System.out.println(query);
		    //ResultSet rs = st.executeQuery(query);
			Statement st = conn.createStatement();
		    st.executeUpdate(query);	      
		    st.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
}













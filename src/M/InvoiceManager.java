package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Common.GlobalData;
import V.Main;

public class InvoiceManager {

	public static void saveNewInvoice() {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO `invoice`(`invoice_id`, `customer_id`, `status`, `date`) VALUES (?, ?, ?, ?);";
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			
			st.setInt(1, 0);
			st.setInt(2, Main.customerDB.customer_id);
			st.setString(3, "waiting confirmation");
			st.setString(4, getTimeCur());
			st.executeUpdate();
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static int getLastestInvoiceId() {
		int id = 0;
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
		    String query = "SELECT * FROM `invoice` ORDER BY `invoice_id` DESC;";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    if(rs.next()) {
		    	id = rs.getInt(1);
		    }
		    
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return id;
	}
	
	public static ArrayList<InvoiceDB> getAllInvoiceCustomer(int customer_id) {
		ArrayList<InvoiceDB> list = new ArrayList<InvoiceDB>();
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM `invoice` WHERE `customer_id` = "+customer_id;
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	InvoiceDB invoiceDB = new InvoiceDB();
		    	invoiceDB.invoice_id = rs.getInt(1);
		    	invoiceDB.customer_id = rs.getInt(2);
		    	invoiceDB.status = rs.getString(3);
		    	invoiceDB.date = rs.getString(4);
		    	
		    	list.add(invoiceDB);
		    }
			
		    st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return list;
		
	}
	
	
	public static ArrayList<InvoiceDB> getAllInvoice() {
		ArrayList<InvoiceDB> list = new ArrayList<InvoiceDB>();
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM `invoice`";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	InvoiceDB invoiceDB = new InvoiceDB();
		    	invoiceDB.invoice_id = rs.getInt(1);
		    	invoiceDB.customer_id = rs.getInt(2);
		    	invoiceDB.status = rs.getString(3);
		    	invoiceDB.date = rs.getString(4);
		    	
		    	list.add(invoiceDB);
		    }
			
		    st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return list;
	}
	
	public static void confirmStatus(InvoiceDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "UPDATE `invoice` SET `invoice_id`="+x.invoice_id+",`customer_id`="+x.customer_id+",`status`='Confirm Order',`date`= '"+x.date+"' WHERE `invoice_id` = "+x.invoice_id+";";
			//System.out.println(query);
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
	
	public static void cancelStatus(InvoiceDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "UPDATE `invoice` SET `invoice_id`="+x.invoice_id+",`customer_id`="+x.customer_id+",`status`='Cancel Order',`date`= '"+x.date+"' WHERE `invoice_id` = "+x.invoice_id+";";
			Statement st = conn.createStatement();
		    st.executeUpdate(query);	      
		    st.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static String getTimeCur() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now(); 
		return dtf.format(now).toString();
	}
	
}

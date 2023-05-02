package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import Common.GlobalData;

public class Invoice_Detail_Manager {
	
	public static void saveNewInvoiceDetail(ArrayList<ShirtOrder> list , int invoice_id) {
		
		try {
			
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO `invoice_detail`(`invoice_detail_id`, `invoice_id`, `shirt_custom_id`, `quantity`, `price_total`) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			for(int i=0; i<list.size(); i++) {
				st.setInt(1, 0);
				st.setInt(2, invoice_id);
				st.setInt(3, list.get(i).shirt_id);
				st.setInt(4, list.get(i).qty);
				st.setInt(5, list.get(i).price);
				st.executeUpdate();
			}
			
			st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<Invoice_Detail> getAllInvoice_Detail(int invoice_id){
		
		ArrayList<Invoice_Detail> list = new ArrayList<Invoice_Detail>();
		
		try {
			
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
		    
		    String query = "SELECT * FROM `invoice_detail` WHERE `invoice_id` = "+invoice_id+" ;";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	Invoice_Detail invoice_Detail = new Invoice_Detail();
		    	invoice_Detail.invoice_detail_id = rs.getInt(1);
		    	invoice_Detail.invoice_id = rs.getInt(2);
		    	invoice_Detail.shirt_custom_id = rs.getInt(3);
		    	
		    	String str  = ShirtManager.getDesignNameAndSize(invoice_Detail.shirt_custom_id);
		    	invoice_Detail.shirt_name = str.substring(0,str.indexOf(","));
		    	invoice_Detail.shirt_size = str.substring(str.indexOf(",")+1);
		    	
		    	invoice_Detail.quantity = rs.getInt(4);
		    	invoice_Detail.total_price = rs.getInt(5);
		    	invoice_Detail.unit_price = invoice_Detail.total_price/invoice_Detail.quantity;
		    	
		    	list.add(invoice_Detail);
		    }
			
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return list;
	}
	
}

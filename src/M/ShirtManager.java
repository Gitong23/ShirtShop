package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import Common.GlobalData;

public class ShirtManager {
	
	public static void saveNewShirtsize(int design_id) {
		
		String size[] = new String[] {"S", "M", "L", "XL", "XXL"};
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO `shirt_custom` (`shirt_custom_id`, `design_id`, `size`) VALUES (?, ?, ?);";
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			for(int i=0; i<5; i++) {
				st.setInt(1, 0);
				st.setInt(2, design_id);
				st.setString(3, size[i]);
				st.executeUpdate();
			}
			
			st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void deleteShirt(DesignDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			String query = "DELETE FROM shirt_custom WHERE design_id="+x.design_id+";";
		    Statement st = conn.createStatement();
		    st.executeUpdate(query);	      
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static int getShirtId(int design_id, String size) {
		
		int id = 0;
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			String query = "SELECT * FROM `shirt_custom` WHERE `design_id`="+design_id +" AND `size` = '"+size+"';";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    if(rs.next())id = rs.getInt(1);
		    st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		
		return id;
	}
	
	public static String getDesignNameAndSize(int shirt_id) {
		int designId = 0;
		String size="";
		String designName = "";
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM `shirt_custom` WHERE `shirt_custom_id` = "+shirt_id+";";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    if(rs.next()) {
		    	designId = rs.getInt(2);
		    	size = rs.getString(3);
		    }
		    
		    query = "SELECT * FROM  design WHERE design_id = "+designId;
		    rs = st.executeQuery(query);
		    if(rs.next())designName = rs.getString(4);
		    
		    st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		return designName+","+size;
	}
	
}





























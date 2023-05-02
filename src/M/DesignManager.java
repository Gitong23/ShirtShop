package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.jdbc.PreparedStatement;

import Common.GlobalData;

public class DesignManager {
	
	public static void saveNewDesign(DesignDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "INSERT INTO design VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			st.setInt(1, 0);
			st.setInt(2, x.customer_id);
			st.setInt(3, x.shirt_background_id);
			st.setString(4, x.design_name);
			
			BufferedImage[] arrImg = new BufferedImage[] {x.img1_front_large, x.img2_back_large, x.img3_front_small, x.img4_back_small};
			
			for(int i=0; i<4; i++) {
				if(arrImg[i] != null) { 
			    	 ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			    	 ImageIO.write(arrImg[i] , "png", outStream);
			    	 byte[] buffer = outStream.toByteArray();
			    	 st.setBytes(5+i, buffer);
			    	 outStream.close();
			     }
			     else {
			    	 byte[] buffer = new byte[0];
			    	 st.setBytes(5+i, buffer);
			     }
			}
			st.executeUpdate();	      
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static ArrayList<DesignDB> getAlluserDesign(int xCustomer_id){
		ArrayList<DesignDB> list = new ArrayList<DesignDB>();
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
		    Class.forName(myDriver);
		    Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
		    String query = "SELECT * FROM design WHERE customer_id = "+xCustomer_id;
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    while(rs.next()) {
		    	DesignDB designDB = new DesignDB();
		    	designDB.design_id = rs.getInt(1);
		    	designDB.customer_id = rs.getInt(2);
		    	designDB.shirt_background_id = rs.getInt(3);
		    	designDB.design_name = rs.getString(4);
		    	
		    	//byte array input to buffed image
		    	
		    	byte[] img1_byte = rs.getBytes(5);
		    	byte[] img2_byte = rs.getBytes(6);
		    	byte[] img3_byte = rs.getBytes(7);
		    	byte[] img4_byte = rs.getBytes(8);
		    	    	
		    	BufferedImage bufferImg1 = null;
		    	BufferedImage bufferImg2 = null;
		    	BufferedImage bufferImg3 = null;
		    	BufferedImage bufferImg4 = null;
		    	
		    	if(img1_byte == null || img1_byte.length == 0) {
		        	
		        }
		        else {
			        ByteArrayInputStream bais = new ByteArrayInputStream(img1_byte);
			        bufferImg1 = ImageIO.read(bais);
			        bais.close();
		        }
		    	
		    	if(img2_byte == null || img2_byte.length == 0) { 
		        	
		        }
		        else {
			        ByteArrayInputStream bais = new ByteArrayInputStream(img2_byte);
			        bufferImg2 = ImageIO.read(bais);
			        bais.close();
		        }
		    	
		    	if(img3_byte == null || img3_byte.length == 0) {
		        	
		        }
		        else {
			        ByteArrayInputStream bais = new ByteArrayInputStream(img3_byte);
			        bufferImg3 = ImageIO.read(bais);
			        bais.close();
		        }
		    	
		    	if(img4_byte == null || img4_byte.length == 0) {
		        	
		        }
		        else {
			        ByteArrayInputStream bais = new ByteArrayInputStream(img4_byte);
			        bufferImg4 = ImageIO.read(bais);
			        bais.close();
		        }
		    	
		    	designDB.img1_front_large = bufferImg1;
		    	designDB.img2_back_large = bufferImg2;
		    	designDB.img3_front_small =  bufferImg3;
		    	designDB.img4_back_small = bufferImg4;
		    	
		    	list.add(designDB);
		    }
		    st.close();
		    
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return list;
	}
	
	public static void editDesign(DesignDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			//String query = "UPDATE design SET VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			String query = "UPDATE design SET design_id = ? , customer_id = ? , shirt_background_id = ? , design_name = ?,"
					+ " img1_front_large = ? , img2_back_large = ? , img3_front_small = ? , img4_back_small = ?"+
					" WHERE design_id ="+x.design_id;
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(query);
			
			st.setInt(1, x.design_id);
			st.setInt(2, x.customer_id);
			st.setInt(3, x.shirt_background_id);
			st.setString(4, x.design_name);
			
			BufferedImage[] arrImg = new BufferedImage[] {x.img1_front_large, x.img2_back_large, x.img3_front_small, x.img4_back_small};
			
			for(int i=0; i<4; i++) {
				if(arrImg[i] != null) { 
			    	 ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			    	 ImageIO.write(arrImg[i] , "png", outStream);
			    	 byte[] buffer = outStream.toByteArray();
			    	 st.setBytes(5+i, buffer);
			    	 outStream.close();
			     }
			     else {
			    	 byte[] buffer = new byte[0];
			    	 st.setBytes(5+i, buffer);
			     }
			}
			st.executeUpdate();	      
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static void deleteDesign(DesignDB x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			String query = "DELETE FROM design WHERE design_id="+x.design_id+";";
		    Statement st = conn.createStatement();
		    st.executeUpdate(query);	      
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
	}
	
	public static int getLastestDesignId() {
		int id = 0;
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM `design` ORDER BY `design_id` DESC;";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);	

		    if(rs.next()) {
		    	id = rs.getInt(1);
		    }
		    st.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
		    System.err.println(e.getMessage());
		}
		
		return id;
	}
	
}

















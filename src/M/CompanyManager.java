package M;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Common.GlobalData;

public class CompanyManager {

	public static Company getCompanyInfo() {
		
		Company company = new Company();
		
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "SELECT * FROM `company_info`";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    
		    if(rs.next()) {
		    	company.id = rs.getInt(1);
		    	company.name = rs.getString(2);
		    	company.address = rs.getString(3);
		    	company.road = rs.getString(4);
		    	company.ket = rs.getString(5);
		    	company.kwang = rs.getString(6);
		    	company.provice = rs.getString(7);
		    	company.pos_id = rs.getString(8);
		    	company.phone = rs.getString(9);
		    	company.email = rs.getString(10);
		    }
			
		    st.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		
		return company;
	}
	
	public static void editCompany(Company x) {
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://"+GlobalData.DATABASE_LOCATION+":"+GlobalData.DATABASE_PORT+"/"+GlobalData.DATABASE_DATABASE_NAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);
			
			String query = "UPDATE `company_info` SET `id`= "+ x.id+",`company_name`='"+x.name+"',`address`='"+x.address
					+ "' ,`road`='"+x.road+"',`ket`='"+x.ket+"',`kwang`='"+x.kwang+"'"
					+ ",`provice`='"+x.provice+"',`pos_id`='"+x.pos_id+"',`phone`='"+x.phone+"'"
					+ ",`email`='"+x.email+"' WHERE id = 1;";
			
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

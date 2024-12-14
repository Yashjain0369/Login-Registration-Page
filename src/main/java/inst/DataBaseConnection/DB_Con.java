package inst.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Con {

	
	
	public static  Connection   getConnection() {
		Connection con = null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mvc_db" ,  "root" , "Anshjain9002@");
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
return con;
	}
	
	
	
}

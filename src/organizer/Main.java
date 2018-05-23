package organizer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // load JDBC driver	
		
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kompo_db?useUnicode=true&"
					+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
					+ "serverTimezone=UTC", "root", "");
			System.out.println("Connected to database");
			conn.close();
		}
		catch(Exception e) {
			System.out.println("Could not connect to DB: " + e);
		}
		
	}

}

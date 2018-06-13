package JavaOrganizer.app;

import java.awt.EventQueue;
import java.sql.DriverManager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import JavaOrganizer.view.CalendarWindow;

public class Main 
{
	public static void main(String[] args) {
		testDbConnection();
		setupUI();
	}
	
	/**
	 * Testuje polaczenie z baza danych,
	 * w razie niepowodzenia wypisuje komunikat
	 */
	private static void testDbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // load JDBC driver	
		
			Connection conn = null;
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/kompo_db?useUnicode=true&"
					+ "useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
					+ "serverTimezone=UTC", "root", "gate33");
			System.out.println("Connected to database");
			conn.close();
		}
		catch(Exception e) {
			System.out.println("Could not connect to DB: " + e);
		}
	}
	
	/**
	 * Uruchamia aplikacje i interfejs graficzny
	 */
	private static void setupUI() {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				CalendarWindow calendarWindow = new CalendarWindow();
				calendarWindow.setTitle("Java Organizer");
				calendarWindow.setVisible(true);
			}
		});
	}
}

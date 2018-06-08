package JavaOrganizer.repository;

import java.sql.*;

import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Calendar;

public class CalendarJdbcRepository implements CalendarRepository {

	private Calendar mCalendar;
	
	public CalendarJdbcRepository(Calendar calendar)
	{
		super();
		this.mCalendar = calendar;
	}


	public void importObjects() throws RepositoryException
	{
		Connection conn = null;
		Statement stmt = null;
		
		System.out.println("jdbc import test method");
		
		//TODO implement import from db
		
	}
	
	
	public void exportObjects() throws RepositoryException
	{

		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kompo_db", "root", "gate33");
			stmt = conn.createStatement();
			String sql = "SELECT DATABASE()";
			ResultSet rs = stmt.executeQuery(sql);
			
			
			rs.close();
			stmt.close();
			conn.close();
	    }
		catch(SQLException se)
		{
	      se.printStackTrace();
	      throw new RepositoryException();
	    }
		catch(ClassNotFoundException e)
		{
		      e.printStackTrace();
		      throw new RepositoryException();
		}
		
		//TODO implement export to db
		System.out.println("jdbc export test method");
		
	}

	
}

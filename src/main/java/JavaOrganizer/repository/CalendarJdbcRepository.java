package JavaOrganizer.repository;

import java.sql.*;
import java.time.LocalDateTime;
import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Calendar;
import JavaOrganizer.model.Event;

public class CalendarJdbcRepository implements CalendarRepository {

	private Calendar mCalendar;
	Connection conn = null;
	Statement stmt = null;
	
	private final String dbclass = "com.mysql.jdbc.Driver";
	
	public CalendarJdbcRepository(Calendar calendar)
	{
		super();
		this.mCalendar = calendar;
	}


	public void importObjects() throws RepositoryException
	{
		
		try
		{
			Class.forName(dbclass);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kompo_db?useUnicode=true&characterEncoding=utf-8", "root", "gate33");
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT * FROM events";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long repId  = rs.getLong("event_id");
				String repTitle = rs.getString("title");
				String repDescription = rs.getString("description");
				String repLocation = rs.getString("location");
				LocalDateTime repStartDate = rs.getTimestamp("start_date").toLocalDateTime();
				LocalDateTime repRemindDate = rs.getTimestamp("remind_date").toLocalDateTime();
		
				
				System.out.println("tytul : " + repTitle + " \\>");
				System.out.println("loc : " + repLocation + " \\>");
				System.out.println("data : " + repStartDate + " \\>");
				
				mCalendar.addEvent(new Event(repId,repTitle,repDescription,repLocation,repStartDate,repRemindDate));

			}
			
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
		
	}
	
	
	public void exportObjects() throws RepositoryException
	{

		
		try
		{
			Class.forName(dbclass);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kompo_db", "root", "gate33");
			stmt = conn.createStatement();

			
			for(Event e : mCalendar.getEventsList())
			{
				
				java.sql.Date sqlStartDate = java.sql.Date.valueOf(e.getStartingDate().toLocalDate());
				java.sql.Date sqlRemindDate = java.sql.Date.valueOf(e.getRemindDate().toLocalDate());

				String querry = "INSERT INTO events VALUES (" +	e.getM_Id() + ",'" + e.getTitle() + "','" +
						e.getDescription() + "','" + e.getLocation() + "','" + sqlStartDate +
						"','" +	sqlRemindDate + "');";
				stmt.executeUpdate(querry);
			}
			
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

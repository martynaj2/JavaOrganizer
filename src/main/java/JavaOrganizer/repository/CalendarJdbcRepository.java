package JavaOrganizer.repository;

import java.sql.*;
import java.time.LocalDateTime;

import JavaOrganizer.controller.CalendarManager;
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kompo_db?useUnicode=true&characterEncoding=utf-8",
											   "root", "gate33");
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT * FROM events";
			ResultSet rs = stmt.executeQuery(sql);
			
			mCalendar.clearCalendar();
			
			Long maximumEventId = 0L;
			int eventsImported = 0;
			while(rs.next()) {
				Long repId  = rs.getLong("event_id");
				String repTitle = rs.getString("title");
				String repDescription = rs.getString("description");
				String repLocation = rs.getString("location");
				LocalDateTime repStartDate = rs.getTimestamp("start_date").toLocalDateTime();
				LocalDateTime repRemindDate = rs.getTimestamp("remind_date").toLocalDateTime();
		
				try {
					CalendarManager.getInstance().getEventById(repId);
				}
				catch(IndexOutOfBoundsException e1) {
					// wyjatek rzucony - nie znaleziono eventu z takim id, mozemy go dodac
					if(repId > maximumEventId) maximumEventId = repId;
					System.out.println("Importing event from database");
					System.out.println("\ttitle: " + repTitle + ", location: " + repLocation + ", start_date: " + repStartDate.toString());
					++eventsImported;
					mCalendar.addEvent(new Event(repId,repTitle,repDescription,repLocation,repStartDate,repRemindDate));
				}
			}
			
			CalendarManager.nextEventId = maximumEventId + 1;
			
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Finished importing events, " + eventsImported + " events imported.");
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
			
			Statement stDelete = conn.createStatement();
			stDelete.executeUpdate("DELETE FROM events WHERE 1=1");
			stDelete.close();
			
			stmt = conn.createStatement();
			
			for(Event e : mCalendar.getEventsList())
			{
				
				java.sql.Timestamp sqlStartDate = java.sql.Timestamp.valueOf(e.getStartingDate());
				java.sql.Timestamp sqlRemindDate = java.sql.Timestamp.valueOf(e.getRemindDate());

				String querry = "INSERT INTO events (event_id,title,description,location,start_date,remind_date) VALUES (" +
						e.getId() +
						", '" + e.getTitle() + "'" +
						",'" + e.getDescription() + "'" + 
						",'" + e.getLocation() + "'" + 
						",'" + sqlStartDate + "'" +
						",'" +	sqlRemindDate + "'" +
						");";
				
				System.out.println(querry);
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

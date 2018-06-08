package JavaOrganizer.repository;

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
		
		System.out.println("jdbc import test method");
		
		//TODO implement import from db
		
	}
	
	
	public void exportObjects() throws RepositoryException
	{
		
		//TODO implement export to db
		System.out.println("jdbc export test method");
		
	}

	
}

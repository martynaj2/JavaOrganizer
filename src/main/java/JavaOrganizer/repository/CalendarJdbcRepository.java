package JavaOrganizer.repository;

import JavaOrganizer.model.Calendar;

public class CalendarJdbcRepository implements CalendarRepository {

	private Calendar mCalendar;
	
	public CalendarJdbcRepository(Calendar calendar)
	{
		this.mCalendar = calendar;
	}


	public void importObjects()
	{
		
		System.out.println("jdbc import test method");
		
		//TODO implement import from db
		
	}
	
	
	public void exportObjects()
	{
		
		//TODO implement export to db
		System.out.println("jdbc export test method");
		
	}

	
}

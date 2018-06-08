package JavaOrganizer.repository;

import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Calendar;

public class CalendarXmlRepository implements CalendarRepository{

	private Calendar mCalendar;
	
	public CalendarXmlRepository(Calendar calendar)
	{
		super();
		this.mCalendar = calendar;
	}

	
	
	public void importObjects() throws RepositoryException
	{
		// TODO Auto-generated method stub
		System.out.println("xml import test method");
		
	}

	public void exportObjects() throws RepositoryException
	{
		System.out.println("xml export test method");
		
	}

}

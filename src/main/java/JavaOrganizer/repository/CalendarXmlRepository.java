package JavaOrganizer.repository;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import com.thoughtworks.xstream.XStream;

import JavaOrganizer.controller.CalendarManager;
import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.*;

public class CalendarXmlRepository implements CalendarRepository{

	Class<?>[] classes = new Class[] { Calendar.class, Event.class };
	
	private Calendar mCalendar;
	private Calendar importCalendar = null;
	
	XStream xstream = null;
	FileOutputStream fos = null;
	
	
	public CalendarXmlRepository(Calendar calendar)
	{
		super();
		this.mCalendar = calendar;
		xstream = new XStream();
		
		xstream.alias("calendar", Calendar.class);
		xstream.alias("event", Event.class);
			
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(classes);
		
	}
	
	public void importObjects() throws RepositoryException
	{		
		try
		{
			Integer fNumber = new File("xml").listFiles().length;
			importCalendar = (Calendar)xstream.fromXML( new FileReader("xml\\xml_export_"+ --fNumber +".xml"));
			
			Long maximumEventId = 0L;
			int eventsImported = 0;
			
			for(Event e : importCalendar.getEventsList())
			{
				Long repId  = e.getId();
				String repTitle = e.getTitle();
				String repDescription = e.getDescription();
				String repLocation = e.getDescription();
				LocalDateTime repStartDate = e.getStartingDate();
				LocalDateTime repRemindDate = e.getRemindDate();
		
				try {
					CalendarManager.getInstance().getEventById(repId);
				}
				catch(IndexOutOfBoundsException e1) {
					// wyjatek rzucony - nie znaleziono eventu z takim id, mozemy go dodac
					System.out.println("Importing event from XML");
					System.out.println("\ttitle: " + repTitle + ", location: " + repLocation + ", start_date: " + repStartDate.toString());
					++eventsImported;
					mCalendar.addEvent(new Event(repId,repTitle,repDescription,repLocation,repStartDate,repRemindDate));
					if(repId > maximumEventId) maximumEventId = repId;
				}
			}
			CalendarManager.nextEventId = maximumEventId + 1;
			System.out.println("Finished importing events, " + eventsImported + " events imported.");
	
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			throw new RepositoryException();
		}
	}

	
	public void exportObjects() throws RepositoryException
	{
		try
		{		
			Integer fNumber = new File("xml").listFiles().length;
			
			xstream.toXML(mCalendar, new FileWriter("xml\\xml_export_"+ fNumber +".xml"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new RepositoryException();
		}
	}
}

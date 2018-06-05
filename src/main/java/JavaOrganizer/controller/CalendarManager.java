package JavaOrganizer.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import JavaOrganizer.model.Calendar;
import JavaOrganizer.model.Event;

public class CalendarManager {

	private Calendar mCalendar;
	private static CalendarManager instance = null;
	
	
	
	//conctructor for calendar manager
	protected CalendarManager() {
		mCalendar = new Calendar();
	}
	
	
	//instance for manager - singelton model = 1 instane in program
	public static CalendarManager getInstance() {
		if(instance == null)
			instance = new CalendarManager();
		return instance;
	}
	
	
	//***********************************************************
	// NEW EVENT
	//***********************************************************
	
	// add new event as object
	
	public void addNewEvent(Event event) {
		mCalendar.addEvent(event);
	}
	
	// add new event as conctructor
	
	public void addNewEvent(String title, String description, String location,
			LocalDateTime start, LocalDateTime end) {
		mCalendar.addEvent(new Event(title, description, location, start, end));
	}
	
	
	//***********************************************************
	// GET EVENTS
	//***********************************************************
	
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> result = new ArrayList<Event>();
		for(Event e : mCalendar.getEventsList())
			result.add(e.clone());
		return result;
	}
	
	
	//***********************************************************
	// EXPORT/ IMPORT METHODS
	//***********************************************************
	public void importDB()
	{
		System.out.println("imported from DB");
	}
	
	public void importXML()
	{
		System.out.println("imported from XML");
	}
	
	public void exportDB()
	{
		System.out.println("exported to DB");
	}
	
	public void exportXML()
	{
		System.out.println("exported to XML");
	}
	
}

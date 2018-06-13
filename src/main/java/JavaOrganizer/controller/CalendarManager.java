package JavaOrganizer.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Calendar;
import JavaOrganizer.model.Event;
import JavaOrganizer.repository.CalendarJdbcRepository;
import JavaOrganizer.repository.CalendarXmlRepository;

public class CalendarManager {

	private Calendar mCalendar;
	public static Long nextEventId = 0L;
	private static CalendarManager instance = null;
	
	//conctructor for calendar manager
	protected CalendarManager() {
		mCalendar = new Calendar();
	}
	
	//instance for manager 
	public static CalendarManager getInstance() {
		if(instance == null)
			instance = new CalendarManager();
		return instance;
	}
	
	//! Zwraca liste eventow w kalendarzu ktore maja ustawiona taka sama date jak `day`
	public List<Event> getEventsFromDay(LocalDate day) {
		List<Event> chosenEvents = new ArrayList<Event>();
		for(Event ev : mCalendar.getEventsList()) {
			if(ev.getStartingDate().toLocalDate().isEqual(day)) {
				chosenEvents.add(ev);
			}
		}
		return chosenEvents;
	}	
	
	//! Metoda statyczna, nie potrzebuje obiektu Calendar do wywolania
	//! Zwraca ilosc dni w miesiacu na podstawie numeru miesiaca i roku
	public int getNumberOfDays(int month, int year) {
		return Calendar.getNumberOfDays(month, year);
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
			LocalDateTime start, LocalDateTime end, LocalDateTime remind) {
		mCalendar.addEvent(new Event(title, description, location, start, end, remind));
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
	// REMOVE EVENTS
	//***********************************************************
	
	public void removeEventById(Long id) {
		System.out.println("Removing event with id " + id);
		for(Event e : mCalendar.getEventsList()) {
			if(e.getId() == id) {
				mCalendar.getEventsList().remove(e);
			}
		}
		try {
			exportDB();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	//***********************************************************
	// EXPORT/ IMPORT METHODS
	//***********************************************************
	
	public void convertEventToGoogle(Long id) {
		String result = "Subject,Start Date,Start Time,Description,Location\n";
		for(Event e : mCalendar.getEventsList()) {
			if(e.getId() == id) {
				result += e.getTitle().replaceAll(",", "") + ",";
				result += e.getStartingDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + ",";
				result += e.getStartingDate().format(DateTimeFormatter.ofPattern("hh:mm a")) + ",";
				result += e.getDescription().replaceAll(",", "") + ",";
				result += e.getLocation().replaceAll(",", "");
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
				  try {
					  BufferedWriter writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
					  writer.write(result);
					  writer.close();
				  }
				  catch(Exception e1) { }
				}
			}
		}
	}
	
	
	//JDBC PART
	public void importDB() throws RepositoryException
	{
		CalendarJdbcRepository repository = new CalendarJdbcRepository(mCalendar);
		
		repository.importObjects();
		
		System.out.println("imported from DB");
	}
	
	public void exportDB() throws RepositoryException
	{
		CalendarJdbcRepository repository = new CalendarJdbcRepository(mCalendar);
		
		repository.exportObjects();
			
		System.out.println("exported to DB");
	}
	
	
	// XML PART
	public void importXML() throws RepositoryException
	{
		CalendarXmlRepository repository = new CalendarXmlRepository(mCalendar);
		
		repository.importObjects();		
		
		System.out.println("imported from XML");
	}
	
	public void exportXML() throws RepositoryException
	{	
		CalendarXmlRepository repository = new CalendarXmlRepository(mCalendar);
		
		repository.exportObjects();
		
		System.out.println("exported to XML");
	}
	
}

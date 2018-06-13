package JavaOrganizer.controller;

import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.channels.ShutdownChannelGroupException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Calendar;
import JavaOrganizer.model.Event;
import JavaOrganizer.repository.CalendarJdbcRepository;
import JavaOrganizer.repository.CalendarXmlRepository;

public class CalendarManager {

	private Calendar mCalendar;
	public static Long nextEventId = 0L;
	private static CalendarManager instance = null;
	
	/**
	 * Konstruktor klasy CalendarManager
	 */
	protected CalendarManager() {
		mCalendar = new Calendar();
	}
	
	/**
	 * Zwraca instancje singletonu CalendarManager
	 * @return instancja CalendarManager
	 */
	public static CalendarManager getInstance() {
		if(instance == null)
			instance = new CalendarManager();
		return instance;
	}
		
	/**
	 *  Zwraca ilosc dni w miesiacu na podstawie numeru miesiaca i roku
	 * @param month miesiac
	 * @param year rok
	 * @return ilosc dni w miesiacuna podstawie numeru miesiaca i roku
	 */
	public int getNumberOfDays(int month, int year) {
		return Calendar.getNumberOfDays(month, year);
	}
	
	//***********************************************************
	// NEW EVENT
	//***********************************************************
	
	/**
	 * Dodaje nowe zdarzenie do kalendarza
	 * @param event obiekt zdarzenia
	 */
	public void addNewEvent(Event event) {
		mCalendar.addEvent(event);
	}
	
	/**
	 * Dodaje nowe zdarzenie do kalendarza, tworzac obiekt zdarzenia na podstawie parametrow
	 * @param title tytul zdarzenia
	 * @param description opis zdarzenia
	 * @param location miejsce zdarzenia
	 * @param start data poczatkowa
	 * @param end data koncowa
	 * @param remind data przypomnienia
	 */
	public void addNewEvent(String title, String description, String location,
			LocalDateTime start, LocalDateTime end, LocalDateTime remind) {
		mCalendar.addEvent(new Event(title, description, location, start, end, remind));
	}
	
	
	//***********************************************************
	// GET EVENTS
	//***********************************************************
	
	/**
	 * Zwraca wszystkie zdarzenia z kalendarza
	 * @return lista obiektow klasy Event
	 */
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> result = new ArrayList<Event>();
		for(Event e : mCalendar.getEventsList())
			result.add(e.clone());
		return result;
	}
	
	/**
	 * Zwraca zdarzenie o podanym indeksie,
	 * jesli takie nie istnieje - rzuca wyjatek IndexOutOfBoundsException
	 * @param id indeks zdarzenia
	 * @return obiekt klasy Event
	 * @throws IndexOutOfBoundsException
	 */
	public Event getEventById(Long id) throws IndexOutOfBoundsException {
		for(Event e : mCalendar.getEventsList()) {
			if(e.getId() == id) {
				return e;
			}
		}
		throw new IndexOutOfBoundsException("Nie znaleziono eventu z podanym ID");
	}
	
	/**
	 * Zwraca liste eventow w kalendarzu ktore maja ustawiona taka sama date jak `day`
	 * @param day
	 * @return lista eventow z danego dnia
	 */
	public List<Event> getEventsFromDay(LocalDate day) {
		List<Event> chosenEvents = new ArrayList<Event>();
		for(Event ev : mCalendar.getEventsList()) {
			if(ev.getStartingDate().toLocalDate().isEqual(day)) {
				chosenEvents.add(ev);
			}
		}
		return chosenEvents;
	}	
	
	/**
	 * Zwraca liste eventow w kalendarzu z danego dnia wg okreslonego filtru (rano, wieczorem itp.)
	 * @param day dzien
	 * @param filterName nazwa filtru (rano, poludnie, wieczorem, wszystkie)
	 * @return wybrane zdarzenia
	 */
	public List<Event> filterEventsFromDay(LocalDate day, final String filterName) {
		if(filterName.equals("wszystkie")) {
			return getEventsFromDay(day);
		}
		
		List<Event> filteredEvents = new ArrayList<Event>();
		for(Event ev : getEventsFromDay(day)) {
			LocalTime time = ev.getStartingDate().toLocalTime();
			if(filterName.equals("rano") && time.getHour() >= 0 && time.getHour() < 12) {
				filteredEvents.add(ev);
			}
			else if(filterName.equals("południe") && time.getHour() >= 12 && time.getHour() < 18) {
				filteredEvents.add(ev);
			}
			else if(filterName.equals("wieczorem") && time.getHour() >= 18 && time.getHour() < 24) {
				filteredEvents.add(ev);
			}
		}
		return filteredEvents;
	}
	
	/**
	 * Wyswietla przypomnienia dla wydarzen, ktore oczekuja przypomnien w danej chwili
	 * @param time aktualna data i czas
	 */
	public void displayReminders(LocalDateTime time){
		for(Event ev : mCalendar.getEventsList()) {
			if (ev.getRemindDate().truncatedTo(ChronoUnit.MINUTES).isEqual(time.truncatedTo(ChronoUnit.MINUTES)) &&
			    ev.getRemindDate().until(time, ChronoUnit.SECONDS) < 1) {
				String soundName = "resources/alert.mp3";    
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				}
				catch(Exception e1) {}
				
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(null, "Masz nadchodzace wydarzenie " + ev.getTitle() +
						" dnia " + ev.getStartingDate().format(DateTimeFormatter.ISO_LOCAL_DATE) +
						" o godzinie " + ev.getStartingDate().format(DateTimeFormatter.ISO_LOCAL_TIME));
			}	
		}
	}
	
	
/*	//! Zwraca liste eventow w kalendarzu ktore maja ustawiona taka sama date jak `day`
	public List<Event> getRemindersFromTime(LocalDateTime time) {
		List<Event> chosenEvents = new ArrayList<Event>();
		for(Event ev : mCalendar.getEventsList()) {
			if(ev.getStartingDate().toLocalDate().isEqual(day)) {
				chosenEvents.add(ev);
			}
		}
		return chosenEvents;
	}	*/
	
	//***********************************************************
	// REMOVE EVENTS
	//***********************************************************
	
	/**
	 * Usuwa zdarzenie o podanym id jesli takie istnieje
	 * @param id id zdarzenia
	 */
	public void removeEventById(Long id) {
		System.out.println("Removing event with id " + id);
		for(Event e : mCalendar.getEventsList()) {
			if(e.getId() == id) {
				mCalendar.getEventsList().remove(e);
				break;
			}
		}
		try {
			exportDB();
		} catch (RepositoryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * Usuwa wydarzenia starsze niz podana data
	 * @param date wydarzenia starsze niz ta data beda usuwane
	 */
	public void removeEventsOlderThanDate(LocalDate date) {
		mCalendar.getEventsList().removeIf(x -> x.getStartingDate().toLocalDate().isBefore(date));
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
	
	/**
	 * Konwertuje zdarzenie o podanym indeksie do formatu zgodnego z Google Calendar
	 * (plik CSV z odpowiednimi nazwami kolumn jako naglowkiem)
	 * @param id indeks zdarzenia
	 */
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
	
	
	/**
	 * Importuje obiekty zdarzen z bazy danych
	 * @throws RepositoryException
	 */
	public void importDB() throws RepositoryException
	{
		CalendarJdbcRepository repository = new CalendarJdbcRepository(mCalendar);
		
		repository.importObjects();
		
		System.out.println("imported from DB");
	}
	
	/**
	 * Eksportuje aktualne obiekty zdarzen do bazy danych
	 * @throws RepositoryException
	 */
	public void exportDB() throws RepositoryException
	{
		CalendarJdbcRepository repository = new CalendarJdbcRepository(mCalendar);
		
		repository.exportObjects();
			
		System.out.println("exported to DB");
	}
	
	
	/**
	 * Importuje obiekty z pliku XML
	 * @throws RepositoryException
	 */
	public void importXML() throws RepositoryException
	{
		CalendarXmlRepository repository = new CalendarXmlRepository(mCalendar);
		
		repository.importObjects();		
		
		System.out.println("imported from XML");
	}
	
	/**
	 * Eksportuje obiekty do pliku XML
	 * @throws RepositoryException
	 */
	public void exportXML() throws RepositoryException
	{	
		CalendarXmlRepository repository = new CalendarXmlRepository(mCalendar);
		
		repository.exportObjects();
		
		System.out.println("exported to XML");
	}
	
}

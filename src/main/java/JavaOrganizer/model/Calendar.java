package JavaOrganizer.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
	private List<Event> events;
	
	//! Konstruktor.
	//! Tworzy obiekt kalendarza i inicjalizuje liste zdarzen
	public Calendar() {
		this.events = new ArrayList<Event>();
		System.out.println("Utworzono obiekt kalendarza");
	}
	
	//! Metoda statyczna, nie potrzebuje obiektu Calendar do wywolania
	//! Zwraca ilosc dni w miesiacu na podstawie numeru miesiaca i roku
	public static int getNumberOfDays(int month, int year) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			return 31;
		if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;
		if (month != 2)
			return -1;
		if(year > 1582) {
			if(month % 4 == 0 && (month % 100 != 0 && month % 400 == 0))
				return 29;
			else
				return 28;
		}
		else {
			if(month % 4 == 0)
				return 29;
			else
				return 28;
		}
	}
	
	//! Dodaje nowy event do kalendarza
	public void addEvent(Event ev) {
		events.add(ev);
		System.out.println("Dodano do kalendarza wydarzenie " + ev.getTitle());
	}
	
	//! Zwraca liste wszystkich eventow
	public List<Event> getEventsList() {
		return events;
	}
}

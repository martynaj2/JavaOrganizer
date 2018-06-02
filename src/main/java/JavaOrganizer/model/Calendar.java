package JavaOrganizer.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calendar {
	private List<Event> events;
	
	public Calendar() {
		this.events = new ArrayList<Event>();
		System.out.println("Utworzono obiekt kalendarza");
	}
	
	public void addEvent(Event ev) {
		events.add(ev);
		System.out.println("Dodano do kalendarza wydarzenie " + ev.getTitle());
	}
	
	public List<Event> getEventsFromDay(LocalDate day) {
		List<Event> chosenEvents = new ArrayList<Event>();
		for(Event ev : events) {
			if(ev.getStartingDate().toLocalDate() == day) {
				chosenEvents.add(ev);
			}
		}
		return chosenEvents;
	}
}

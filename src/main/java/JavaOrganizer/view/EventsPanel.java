package JavaOrganizer.view;

import java.awt.Color;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JavaOrganizer.controller.CalendarManager;
import JavaOrganizer.model.Event;

@SuppressWarnings("serial")
public class EventsPanel extends JPanel {
	
	// Widgety
	JLabel chosenDayLabel = new JLabel("");
	JLabel eventsCountLabel = new JLabel("");
	
	// Inne
	private CalendarManager calManager;
	
	public EventsPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
		setBackground(new Color(254, 254, 254));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		chosenDayLabel.setBounds(10, 10, 200, 25);
		eventsCountLabel.setBounds(10, 40, 200, 25);
		add(chosenDayLabel);
		add(eventsCountLabel);
	}
	
	public void showPanel() {
	}
	
	public void showEventsFromDay(LocalDate day) {
		chosenDayLabel.setText(day.toString());
		List<Event> events = calManager.getEventsFromDay(day);
		if(events.size() == 0) {
			eventsCountLabel.setText("Brak wydarzeń tego dnia.");
		}
		else {
			eventsCountLabel.setText("Liczba wydarzeń: " + events.size());
		}
	}
}

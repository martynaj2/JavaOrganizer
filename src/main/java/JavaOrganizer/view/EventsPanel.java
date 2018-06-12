package JavaOrganizer.view;

import java.awt.Color;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import JavaOrganizer.controller.CalendarManager;
import JavaOrganizer.model.Event;

@SuppressWarnings("serial")
public class EventsPanel extends JPanel {
	
	// Widgety
	JLabel chosenDayLabel = new JLabel("");
	JLabel eventsCountLabel = new JLabel("");
	JScrollPane eventsScrollPane;
	
	// Inne
	private CalendarManager calManager;
	
	public EventsPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
		setBackground(new Color(254, 254, 254));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		chosenDayLabel.setBounds(10, 10, 200, 25);
		eventsCountLabel.setBounds(10, 40, 200, 25);
		eventsCountLabel.setVerticalAlignment(JLabel.TOP);
		eventsCountLabel.setVerticalTextPosition(JLabel.TOP);
		add(chosenDayLabel);
		add(eventsCountLabel);
	}
	
	public void showPanel() {
	}
	
	public void showEventsFromDay(LocalDate day) {
		chosenDayLabel.setText(day.toString());
		System.out.println("Displaying events at " + day.toString());
		List<Event> events = CalendarManager.getInstance().getEventsFromDay(day);
		if(events.size() == 0) {
			eventsCountLabel.setText("Brak wydarzeń tego dnia.");
		}
		else {
			String content = "Liczba wydarzeń: " + events.size() + "";
			List<String> eventsList = new ArrayList<String>();
			for(Event e : events) {
				eventsList.add(e.toString());
//				 content += "<p>" + e.toString() + "</p>";
			}

			String[] eventsArray = eventsList.toArray(new String[eventsList.size()]);
			JList<String> eventsJList = new JList<String>(eventsArray);
			eventsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			eventsJList.setSelectedIndex(0);
			eventsScrollPane = new JScrollPane(eventsJList);
//			content += "</html>";
			eventsCountLabel.setText(content);
			eventsScrollPane.setBounds(10, 70, 500, 300);
			add(eventsScrollPane);
			
		}
	}
}

package JavaOrganizer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	JButton removeEventButton;
	JButton removeOlderEventsButton;
	JButton saveInGoogleFormatButton;
	JLabel chosenDayLabel = new JLabel("");
	JLabel eventsCountLabel = new JLabel("");
	JScrollPane eventsScrollPane;
	JList<String> eventsJList;
	JComboBox<String> eventFiltersBox;
	
	// Inne
	private CalendarManager calManager;
	private LocalDate currentlyDisplayedDate = LocalDate.now();
	
	public EventsPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
		setBackground(new Color(254, 254, 254));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		eventsScrollPane = new JScrollPane();
		
		saveInGoogleFormatButton = new JButton("Eksportuj do formatu Google");
		saveInGoogleFormatButton.setBounds(300, 480, 200, 25);
		saveInGoogleFormatButton.setVisible(false);
		saveInGoogleFormatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedEventId = EventsPanel.this.eventsJList.getSelectedValue().substring(0, 5).replaceAll(" ", "");
            	Long eventId = Long.parseLong(selectedEventId);
            	CalendarManager.getInstance().convertEventToGoogle(eventId);
            }
        });
		add(saveInGoogleFormatButton);
		
		removeEventButton = new JButton("Usuń wydarzenie");
		removeEventButton.setBounds(155, 480, 140, 25);
		removeEventButton.setVisible(false);
		removeEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// bierzemy pierwsze 5 znakow z elementu listy - 
            	// bo event_id wyswietlamy zawsze na 5 miejscach (tak jak jest napisane w Event toString())
            	String selectedEventId = EventsPanel.this.eventsJList.getSelectedValue().substring(0, 5).replaceAll(" ", "");
            	Long eventId = Long.parseLong(selectedEventId);
            	CalendarManager.getInstance().removeEventById(eventId);
            	showEventsFromDay(currentlyDisplayedDate);
            }
        });
		add(removeEventButton);
		
		removeOlderEventsButton = new JButton("Usuń starsze");
		removeOlderEventsButton.setBounds(10, 480, 140, 25);
		removeOlderEventsButton.setVisible(true);
		removeOlderEventsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedEventId = EventsPanel.this.eventsJList.getSelectedValue().substring(0, 5).replaceAll(" ", "");
            	Long eventId = Long.parseLong(selectedEventId);
            	CalendarManager.getInstance().removeEventsOlderThanDate(currentlyDisplayedDate);
            	System.out.println("Usuwanie wydarzen starszych niz " + eventId);
            }
        });
		add(removeOlderEventsButton);
		
		String[] eventFilters = {"wszystkie", "rano", "południe", "wieczorem"};
		eventFiltersBox = new JComboBox<String>(eventFilters);
		eventFiltersBox.setSelectedIndex(0);
		eventFiltersBox.setBounds(440, 40, 100, 25);
		eventFiltersBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String filter = eventFiltersBox.getSelectedItem().toString();
            	System.out.println("Wybrano filtr: " + filter);
            	List<Event> filteredEvents = CalendarManager.getInstance().filterEventsFromDay(currentlyDisplayedDate, filter);
            	showEvents(filteredEvents);
            }
        });
		add(eventFiltersBox);
		
		chosenDayLabel.setBounds(10, 10, 200, 25);
		eventsCountLabel.setBounds(10, 40, 250, 25);
		eventsCountLabel.setVerticalAlignment(JLabel.TOP);
		eventsCountLabel.setVerticalTextPosition(JLabel.TOP);
		add(chosenDayLabel);
		add(eventsCountLabel);
		
		showEventsFromDay(currentlyDisplayedDate);
	}
	
	public void showPanel() {
	}	
	
	public LocalDate getCurrentlyDisplayedDate() {
		return currentlyDisplayedDate;
	}
	
	public void showEventsFromDay(LocalDate day) {
		List<Event> events = CalendarManager.getInstance().getEventsFromDay(day);
		currentlyDisplayedDate = day;
		chosenDayLabel.setFont(new Font("Helvetica", Font.PLAIN, 14));
		chosenDayLabel.setText(day.toString());
		System.out.println("Displaying events at " + day.toString());
		showEvents(events);
	}
	
	public void showEvents(List<Event> events) {
		remove(eventsScrollPane);
		repaint();
		revalidate();
		if(events.size() == 0) {
			eventsCountLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
			eventsCountLabel.setText("Brak wydarzeń tego dnia.");
			removeEventButton.setVisible(false);
			saveInGoogleFormatButton.setVisible(false);
			eventsScrollPane.setVisible(false);
			eventsScrollPane.repaint();
		}
		else {
			String content = "Liczba wydarzeń: " + events.size() + "";
			removeEventButton.setVisible(true);
			saveInGoogleFormatButton.setVisible(true);
			
			List<String> eventsList = new ArrayList<String>();
			for(Event e : events) {
				eventsList.add(e.toString());
			}
			
			String[] eventsArray = eventsList.toArray(new String[eventsList.size()]);
			eventsJList = new JList<String>(eventsArray);
			eventsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			eventsJList.setSelectedIndex(0);
			eventsScrollPane.repaint();
			eventsScrollPane = new JScrollPane(eventsJList);
			eventsCountLabel.setText(content);
			eventsScrollPane.setBounds(10, 70, 530, 300);
			add(eventsScrollPane);
			
		}
	}
}

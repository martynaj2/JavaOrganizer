package JavaOrganizer.view;

import java.awt.Color;
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
	JButton saveInGoogleFormatButton;
	JLabel chosenDayLabel = new JLabel("");
	JLabel eventsCountLabel = new JLabel("");
	JScrollPane eventsScrollPane;
	JList<String> eventsJList;
	
	// Inne
	private CalendarManager calManager;
	private LocalDate currentlyDisplayedDate;
	
	public EventsPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
		setBackground(new Color(254, 254, 254));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		eventsScrollPane = new JScrollPane();
		
		saveInGoogleFormatButton = new JButton("Save in Google format");
		saveInGoogleFormatButton.setBounds(135, 480, 160, 25);
		saveInGoogleFormatButton.setVisible(false);
		saveInGoogleFormatButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String selectedEventId = EventsPanel.this.eventsJList.getSelectedValue().substring(0, 5).replaceAll(" ", "");
            	Long eventId = Long.parseLong(selectedEventId);
            	CalendarManager.getInstance().convertEventToGoogle(eventId);
            }
        });
		add(saveInGoogleFormatButton);
		
		removeEventButton = new JButton("Remove event");
		removeEventButton.setBounds(10, 480, 120, 25);
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
		currentlyDisplayedDate = day;
		chosenDayLabel.setText(day.toString());
		System.out.println("Displaying events at " + day.toString());
		List<Event> events = CalendarManager.getInstance().getEventsFromDay(day);
		remove(eventsScrollPane);
		repaint();
		revalidate();
		if(events.size() == 0) {
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
			eventsScrollPane.setBounds(10, 70, 500, 300);
			add(eventsScrollPane);
			
		}
	}
}

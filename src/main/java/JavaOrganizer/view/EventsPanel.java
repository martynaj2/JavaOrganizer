package JavaOrganizer.view;

import java.awt.Color;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import JavaOrganizer.controller.CalendarManager;

@SuppressWarnings("serial")
public class EventsPanel extends JPanel {
	
	// Widgety
	JLabel chosenDayLabel = new JLabel("");
	
	// Inne
	private CalendarManager calManager;
	
	public EventsPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
		setBackground(new Color(254, 254, 254));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		chosenDayLabel.setBounds(10, 10, 200, 25);
		add(chosenDayLabel);
	}
	
	public void showPanel() {
	}
	
	public void showEventsFromDay(LocalDate day) {
		chosenDayLabel.setText(day.toString());
	}
}

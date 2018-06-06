package JavaOrganizer.app;

import java.awt.EventQueue;

import JavaOrganizer.view.CalendarWindow;

/**
 * Hello world!
 *
 */
public class Main 
{
	public static void main(String[] args) {

		setupUI();

	}
	
	public static void setupUI(){
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				CalendarWindow calendarWindow = new CalendarWindow();
				calendarWindow.setTitle("Java Organizer");
				calendarWindow.setVisible(true);
			}
		});
	}
}

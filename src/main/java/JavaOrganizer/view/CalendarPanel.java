package JavaOrganizer.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import JavaOrganizer.controller.CalendarManager;

@SuppressWarnings("serial")
public class CalendarPanel extends JPanel {

	// Widgety
	
	// Inne
	private CalendarManager calManager;
	
	private JLabel currentDateTimeLabel = new JLabel("");
	private JLabel calendarMonthYearLabel = new JLabel("");
	private List<JLabel> calendarTiles = new ArrayList<JLabel>();
	private int currentCalendarMonth = LocalDate.now().getMonthValue();
	private int currentCalendarYear = LocalDate.now().getYear();
	
	//! Konstruktor
	public CalendarPanel(CalendarManager manager) {
		this.calManager = manager;
		
		setLayout(null);
	}
	
	//! Pokazuje elementy panelu
	public void showPanel() {
		showCurrentDateTime();
		showCalendar();
		showCalendarTiles();
	}
	
	//! Wyswietla informacje kalendarza
	//! (aktualny miesiac/rok, przyciski do nawigacji
	private void showCalendar() {
		calendarMonthYearLabel.setBounds(200, 100, 200, 25);
		JButton prevMonthButton = new JButton("Poprzedni");
		calendarMonthYearLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		add(calendarMonthYearLabel);
		
		prevMonthButton.setBounds(75, 100, 100, 25);
		prevMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentCalendarMonth -= 1;
                if(currentCalendarMonth < 1) {
                	currentCalendarYear -= 1;
                	currentCalendarMonth = 12;
                }
                showCalendarTiles();
                
            }
        });
		add(prevMonthButton);
		
		JButton nextMonthButton = new JButton("Następny");
		nextMonthButton.setBounds(300, 100, 100, 25);
		nextMonthButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentCalendarMonth += 1;
                if(currentCalendarMonth > 12) {
                	currentCalendarYear += 1;
                	currentCalendarMonth = 1;
                }
                showCalendarTiles();
                
            }
        });
		add(nextMonthButton);
	}
	
	//! Wyswietla kafelki kalendarza
	private void showCalendarTiles() {
		int daysInMonth = calManager.getNumberOfDays(currentCalendarMonth, currentCalendarYear);
		LocalDate calendarDate = LocalDate.of(currentCalendarYear, currentCalendarMonth, 1);
		calendarMonthYearLabel.setText(calendarDate.format(DateTimeFormatter.ofPattern("MM-yyyy")));
		
		// Wyswietlanie kafelkow kalendarza
		System.out.println("Wyswietlam kafelki kalendarza dla " + calendarDate.toString());
		
		for(JLabel tileLabel : calendarTiles) {
			remove(tileLabel);
		}
		calendarTiles.clear();
		revalidate();
		repaint();
		
		int yPosition = 175;
		for(int i=0; i<daysInMonth; ++i) {
			JLabel tile = new JLabel("" + calendarDate.getDayOfMonth(), SwingConstants.CENTER);
			int xPosition = 30 + ((calendarDate.getDayOfWeek().getValue() - 1) * 60); // 0 - Monday, 6 - Sunday
			tile.setBounds(xPosition, yPosition, 50, 50);
			tile.setBorder(BorderFactory.createLineBorder(Color.black));
			if(calendarDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				yPosition += 60;
			add(tile);
			calendarTiles.add(tile);
			calendarDate = calendarDate.plusDays(1);
		}
	}

	//! Wyswietla aktualna date i czas.
	//! Odwieza zegar automatycznie co 1000ms
	private void showCurrentDateTime() {
		currentDateTimeLabel.setBounds(20, 20, 400, 20);
		currentDateTimeLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		add(currentDateTimeLabel);
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				currentDateTimeLabel.setText("Aktualna data: " + dateTime.format(formatter));
			}
		};
		new Timer(1000, taskPerformer).start();
	}
}

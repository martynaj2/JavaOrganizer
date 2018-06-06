package JavaOrganizer.view;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import JavaOrganizer.controller.CalendarManager;

public class CalendarWindow
{
	// Widgety
	private JFrame frame;
	private JMenuBar menuBar;
	private JLabel currentDateTimeLabel = new JLabel("");
	private JLabel calendarTiles[];
	
	// Inne
	private CalendarManager calManager;
	private int currentCalendarMonth = LocalDateTime.now().getMonthValue();
	private int currentCalendarYear = LocalDateTime.now().getYear();
	
	//! Inicjalizuje pola klasy
	//! Tworzy okno, ustala jego wymiary i inne wlasciwosci
	public CalendarWindow()
	{
		calManager = CalendarManager.getInstance();	
		
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(1100, 600);
		getFrame().setLayout(null);
		getFrame().setResizable(false);
		
		initializeMenuBar();
		showCurrentDateTime();
		showCalendarTiles();
	}
	
	//! Tworzy menu i jego zawartosc.
	//! Podpina eventy do poszczegolnych elementow menu
	private void initializeMenuBar()
	{
		menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);
		
		JMenu mnEvent = new JMenu("Event");
		menuBar.add(mnEvent);
		
		JMenu mnExport = new JMenu("Import/Export");
		menuBar.add(mnExport);
		
		JMenu mnConvert = new JMenu("Convert");
		menuBar.add(mnConvert);
		
		JMenu mnAbout= new JMenu("About");
		menuBar.add(mnAbout);
		
		//******************************************************************
		//OPTIONS FOR IMPORT/EXPORT
		//******************************************************************
		JMenuItem mnImportFromDB = new JMenuItem("ImportFromDB");
		JMenuItem mnExportToDB = new JMenuItem("ExportToDB");
		JMenuItem mnImportFromXML = new JMenuItem("ImportFromXML");
		JMenuItem mnImportToXML = new JMenuItem("ImportToXML");
		
		mnExport.add(mnImportFromDB);
		mnExport.add(mnExportToDB);
		mnExport.addSeparator();
		mnExport.add(mnImportFromXML);
		mnExport.add(mnImportToXML);
		
		//ACTION FOR IMPORT DB
			mnImportFromDB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//try catch
					calManager.importDB();
					//refresh LIST
					JOptionPane.showMessageDialog(null, "Calendar imported (DB).");
				}
			});
				
		//ACTION FOR IMPORT XML
			mnImportFromXML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//try catch
						calManager.importXML();
						//refresh LIST
						JOptionPane.showMessageDialog(null, "Calendar imported (XML).");
					}
				});
		
		//ACTION FOR EXPOPRT DB
				mnExportToDB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//try catch
						calManager.exportDB();
						//refresh LIST
						JOptionPane.showMessageDialog(null, "Calendar exported (DB).");
						
					}
				});
		
				
		//ACTION FOR EXPORT XML
				mnImportToXML.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//try catch
						calManager.exportXML();
						//refresh LIST
						JOptionPane.showMessageDialog(null, "Calendar exported (XML).");
						
					}
				});
		
		//******************************************************************
		//OPTION FOR ABOUT
		//******************************************************************
		JMenuItem mnAboutPro = new JMenuItem("About the program");
		mnAbout.add(mnAboutPro);
		
		//ACTION FOR ABOUT BUTTON
		mnAboutPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}
		});
		
		
		//******************************************************************
		//OPTION FOR EVENT
		//******************************************************************
		JMenuItem mnNewEvent = new JMenuItem("Add new event");
		mnEvent.add(mnNewEvent);
		
		//ACTION FOR EVENT BUTTON
		mnNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEventDialog newEventDialog = new NewEventDialog();
				newEventDialog.setVisible(true);
			}
		});	
	}
	
	//! Wyswietla kafelki kalendarza
	private void showCalendarTiles() {
		int daysInMonth = calManager.getNumberOfDays(currentCalendarMonth, currentCalendarYear);
		LocalDate calendarDate = LocalDate.of(currentCalendarYear, currentCalendarMonth, 1);

		JLabel calendarMonthYearLabel = new JLabel(calendarDate.format(DateTimeFormatter.ofPattern("MM-yyyy")));
		calendarMonthYearLabel.setBounds(200, 100, 200, 25);
		calendarMonthYearLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		getFrame().add(calendarMonthYearLabel);
		
		calendarTiles = new JLabel[daysInMonth];
		int yPosition = 200;
		for(int i=0; i<daysInMonth; ++i) {
			calendarTiles[i] = new JLabel("" + calendarDate.getDayOfMonth(), SwingConstants.CENTER);
			int xPosition = 30 + ((calendarDate.getDayOfWeek().getValue() - 1) * 60); // 0 - Monday, 6 - Sunday
			calendarTiles[i].setBounds(xPosition, yPosition, 50, 50);
			calendarTiles[i].setBorder(BorderFactory.createLineBorder(Color.black));
			if(calendarDate.getDayOfWeek() == DayOfWeek.SUNDAY)
				yPosition += 60;
			getFrame().add(calendarTiles[i]);
			calendarDate = calendarDate.plusDays(1);
		}
	}
	
	private void showCurrentDateTime() {
		currentDateTimeLabel.setBounds(20, 20, 400, 20);
		currentDateTimeLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		frame.add(currentDateTimeLabel);
		
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				LocalDateTime dateTime = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
				currentDateTimeLabel.setText("Aktualna data: " + dateTime.format(formatter));
			}
		};
		new Timer(1000, taskPerformer).start();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}

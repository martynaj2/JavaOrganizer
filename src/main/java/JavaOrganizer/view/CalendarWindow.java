package JavaOrganizer.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import JavaOrganizer.controller.CalendarManager;

@SuppressWarnings("serial")
public class CalendarWindow extends JFrame
{
	// Widgety
	private JMenuBar menuBar;
	public EventsPanel eventsPanel;
	public CalendarPanel calendarPanel;
	
	// Inne
	private CalendarManager calManager;
	
	//! Inicjalizuje pola klasy
	//! Tworzy okno, ustala jego wymiary i inne wlasciwosci
	public CalendarWindow()
	{
		calManager = CalendarManager.getInstance();	
		
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1100, 600);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(new Color(240, 240, 240));
		
		initializeMenuBar();
		
		calendarPanel = new CalendarPanel(calManager);
		calendarPanel.setBounds(10, 10, 500, 530);
		add(calendarPanel);
		
		eventsPanel = new EventsPanel(calManager);
		eventsPanel.setBounds(530, 10, 550, 530);
		add(eventsPanel);
		
		calendarPanel.showPanel();
		eventsPanel.showPanel();
	}
	
	//! Tworzy menu i jego zawartosc.
	//! Podpina eventy do poszczegolnych elementow menu
	private void initializeMenuBar()
	{
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
}

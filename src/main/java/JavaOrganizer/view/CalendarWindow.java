package JavaOrganizer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CalendarWindow
{
	private JFrame frame;
	

	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public CalendarWindow()
	{
		
		// i guess load managers for controllers
		initialize();
	}
	
	private void initialize()
	{
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1000, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setSize(1100, 600);
		getFrame().setResizable(false);
		
		
		// MENU BAR
		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);
		
		JMenu mnEvent = new JMenu("Event");
		menuBar.add(mnEvent);
		
		JMenu mnExport = new JMenu("Import/Export");
		menuBar.add(mnExport);
		
		JMenu mnConvert = new JMenu("Convert");
		menuBar.add(mnConvert);
		
		JMenu mnAbout= new JMenu("About");
		menuBar.add(mnAbout);
		
		
		//OPTIONS FOR IMPORT
		JMenuItem mnImportFromDB = new JMenuItem("ImportFromDB");
		JMenuItem mnExportToDB = new JMenuItem("ExportToDB");
		JMenuItem mnImportFromXML = new JMenuItem("ImportFromXML");
		JMenuItem mnImportToXML = new JMenuItem("ImportToXML");
		
		mnExport.add(mnImportFromDB);
		mnExport.add(mnExportToDB);
		mnExport.addSeparator();
		mnExport.add(mnImportFromXML);
		mnExport.add(mnImportToXML);
		
		//OPTION FOR ABOUT		
		JMenuItem mnAboutPro = new JMenuItem("About the program");
		mnAbout.add(mnAboutPro);
		
		//ACTION FOR ABOUT BUTTON
		mnAboutPro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog aboutDialog = new AboutDialog();
				aboutDialog.setVisible(true);
			}
		});
		
	}
	
	
	
}

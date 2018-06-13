package JavaOrganizer.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import JavaOrganizer.controller.CalendarManager;
import JavaOrganizer.exception.RepositoryException;
import JavaOrganizer.model.Event;

import java.time.LocalDate;

public class NewEventDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	public JTextField eventTitle;
	public JTextField eventDesc;
	public JTextField eventLoc;
	public JTextField eventStartDateField;
	public JTextField eventRemindDateField;
	
	public NewEventDialog(LocalDate eventStartDate)
	{
		setTitle("New event (" + eventStartDate.toString() + ")");
		setBounds(400, 250, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(null);
				
		//		title;
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(15, 14, 107, 14);
		contentPanel.add(lblTitle);
		
		eventTitle = new JTextField();
		eventTitle.setBounds(130, 11, 260, 20);
		contentPanel.add(eventTitle);
		
		//		description;
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(15, 44, 107, 14);
		contentPanel.add(lblDesc);
		
		eventDesc = new JTextField();
		eventDesc.setBounds(130, 41, 260, 20);
		contentPanel.add(eventDesc);
		
		//		location;		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(15, 74, 107, 14);
		contentPanel.add(lblLocation);
		
		eventLoc = new JTextField();
		eventLoc.setBounds(130, 71, 260, 20);
		contentPanel.add(eventLoc);
		
		//		start date;
		JLabel lblStartDate = new JLabel("Start date");
		lblStartDate.setBounds(15, 104, 107, 14);
		contentPanel.add(lblStartDate);
		
		LocalDateTime startDate = LocalDateTime.of(eventStartDate, LocalTime.of(12, 0));
		eventStartDateField = new JTextField(startDate.toString());
		eventStartDateField.setBounds(130, 101, 260, 20);
		contentPanel.add(eventStartDateField);
		
		// remind date;		
		JLabel lblRemindDate = new JLabel("Remind date");
		lblRemindDate.setBounds(15, 134, 107, 14);
		contentPanel.add(lblRemindDate);
		
		eventRemindDateField = new JTextField(startDate.minusMinutes(15).toString());
		eventRemindDateField.setBounds(130, 131, 260, 20);
		contentPanel.add(eventRemindDateField);
		
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create New Event");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(NewEventDialog.this.eventTitle.getText().isEmpty() ||
								NewEventDialog.this.eventStartDateField.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Tytuł zdarzenia i data poczatkowa nie moga byc puste!");
							return;
						}
						
						String evTitle = NewEventDialog.this.eventTitle.getText();
						String evDescr = NewEventDialog.this.eventDesc.getText();
						String evLoc = NewEventDialog.this.eventLoc.getText();
						LocalDateTime evStart;
						LocalDateTime evRemind;
						try {
							evStart = LocalDateTime.parse(NewEventDialog.this.eventStartDateField.getText());
							evRemind = LocalDateTime.parse(NewEventDialog.this.eventRemindDateField.getText());
						}
						catch(Exception e) {
							JOptionPane.showMessageDialog(null, "Data w podanym formacie nie jest poprawna!");
							return;
						}
						
						if(evStart.isBefore(evRemind)) {
							JOptionPane.showMessageDialog(null, "Data poczatkowa nie moze poprzedzac daty przypomnienia!");
							return;
						}
						
						Event ev = new Event(evTitle, evDescr, evLoc, evStart, evStart, evRemind);
						System.out.println("Adding new event = " + ev.toString());
						CalendarManager.getInstance().addNewEvent(ev);
						
						try {
							CalendarManager.getInstance().exportDB();
						} catch (RepositoryException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("Create New Event");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		
		setResizable(false);
	}
	
	
}
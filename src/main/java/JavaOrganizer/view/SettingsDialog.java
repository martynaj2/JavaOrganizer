package JavaOrganizer.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import JavaOrganizer.model.Event;

public class SettingsDialog extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	private static CalendarPanel mCalendarPanel;
	private EventsPanel mEventPanel;
	
	/**
	 * Create the dialog. 
	 * @param eventsPanel 
	 * @param calendarPanel 
	 */
	public SettingsDialog(CalendarPanel calendarPanel, EventsPanel eventsPanel) {
		
		
		this.mEventPanel=eventsPanel;
		this.mCalendarPanel=calendarPanel;
		
		
		
		setTitle("Settings");
		setBounds(400, 250, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.EAST);
			{
				JButton okButton = new JButton("Jasny Motyw");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//DOSTH
						
						mCalendarPanel.changeBackgroundLight();
						mEventPanel.changeBackgroundLight();
						
					}
				});
				okButton.setActionCommand("Jasny Motyw");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			getContentPane().add(buttonPane, BorderLayout.WEST);
			{
				JButton okButton = new JButton("Ciemny Motyw");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//DOSTH
						
						mCalendarPanel.changeBackgroundDark();
						mEventPanel.changeBackgroundDark();
						
					}
				});
				okButton.setActionCommand("Ciemny Motyw");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		
		setResizable(false);
	}

}

package JavaOrganizer.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewEventDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	
	
	
	private JTextField eventTitle;
	private JTextField eventDesc;
	private JTextField eventLoc;
	
	public NewEventDialog()
	{
		setTitle("New Event ");
		setBounds(400, 250, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		//contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.setLayout(null);
		

		//		startingDate;
		
		//		endingDate;
		
		
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
		
		
//		eventLoc = new JTextField();
//		eventLoc.setBounds(130, 101, 260, 20);
//		contentPanel.add(eventLoc);
		
		// end date;		
		JLabel lblEndDate = new JLabel("End date");
		lblEndDate.setBounds(15, 134, 107, 14);
		contentPanel.add(lblEndDate);
		
		
//		eventLoc = new JTextField();
//		eventLoc.setBounds(130, 131, 260, 20);
//		contentPanel.add(eventLoc);
		
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Create New Event");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
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

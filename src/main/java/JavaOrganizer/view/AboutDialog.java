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

public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog. 
	 */
	public AboutDialog() {
		setTitle("About");
		setBounds(400, 250, 450, 281);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
			JLabel lblInteractiveCalendarOrganizer = new JLabel("<html><center>Java Calendar / Organizer<br>Programowie Komponentowe 2018</center></html>");
			lblInteractiveCalendarOrganizer.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblInteractiveCalendarOrganizer, BorderLayout.NORTH);
	
			JLabel lblCopyright = new JLabel("<html>Martyna Jasiak 203892   <br>Piotr Olszewski 203957</html>");
			lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblCopyright, BorderLayout.CENTER);
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zamknij");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("Zamknij");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		
		setResizable(false);
	}
}

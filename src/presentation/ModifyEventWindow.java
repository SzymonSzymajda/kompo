package presentation;
import logic.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DateFormatter;

import data.Event;
import data.Person;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;

/**
 * Dialog for adding new Event
 */
@SuppressWarnings("serial")
public class ModifyEventWindow extends JDialog {

	final JPanel contentPanel = new JPanel();
	JTextArea textArea;
	JSpinner spinner;
	int notificationDays = 0;


	
	public ModifyEventWindow(LogicLayer ll, Calendar cal, UserInterface ui, Event oldEvent) {
		setBounds(100, 100, 450, 300);
		this.setTitle("Modify Event");
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel datePanel = new JPanel();
			
			
			Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.HOUR_OF_DAY, 24);
	        calendar.set(Calendar.MINUTE, 0);

	        SpinnerDateModel model = new SpinnerDateModel();
	        model.setValue(calendar.getTime());
	        spinner = new JSpinner(model);
	        spinner.setMinimumSize(new Dimension(200, 20));

	        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
	        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
	        
	        spinner.setEditor(editor);
	        
	        formatter.setAllowsInvalid(false);
	        formatter.setOverwriteMode(true);
	        GridBagLayout gbl_datePanel = new GridBagLayout();
	        gbl_datePanel.columnWidths = new int[]{145, 18, 89, 0};
	        gbl_datePanel.rowHeights = new int[]{25, 0};
	        gbl_datePanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
	        gbl_datePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
	        datePanel.setLayout(gbl_datePanel);
	        {
	        	JLabel label = new JLabel("Event time:");
	        	label.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        	label.setEnabled(false);
	        	GridBagConstraints gbc_label = new GridBagConstraints();
	        	gbc_label.fill = GridBagConstraints.BOTH;
	        	gbc_label.insets = new Insets(0, 0, 0, 5);
	        	gbc_label.gridx = 0;
	        	gbc_label.gridy = 0;
	        	datePanel.add(label, gbc_label);
	        }
			this.getContentPane().add(datePanel, BorderLayout.NORTH);
				        	        	        
			GridBagConstraints gbc_spinner = new GridBagConstraints();
			gbc_spinner.fill = GridBagConstraints.BOTH;
			gbc_spinner.gridx = 2;
			gbc_spinner.gridy = 0;
			datePanel.add(spinner, gbc_spinner);

		}
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEventDescription = new JLabel("Event description:");
		lblEventDescription.setEnabled(false);
		lblEventDescription.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPanel.add(lblEventDescription, BorderLayout.NORTH);
		{
			textArea = new JTextArea();
			contentPanel.add(textArea, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton notificationButton = new JButton("Add notification");
				notificationButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(notificationDays != 0) {
							new ErrorWindow("Notification already set to " + notificationDays);
							return;
						}
						int initial=0, min=0, max=365, increment=1;
						SpinnerNumberModel model =
						new SpinnerNumberModel( initial, min, max, increment );
						JSpinner spinner = new JSpinner(model);
						String message = "Days before the event:";
						int result = JOptionPane.showOptionDialog(null,
								new Object[] { message, spinner},
								"Notification", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null, null, null);
						if(result == JOptionPane.OK_OPTION) {
							int res = Integer.parseInt(spinner.getValue().toString());
							if(res != 0) {
								notificationDays = res;
							}
						}
					}
				});
				buttonPane.add(notificationButton);
				
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String description = textArea.getText();
						if(description == null) {
							description = "";
						}
						Calendar time = Calendar.getInstance();
						time.setTime((Date)spinner.getValue());
						Calendar notification = (Calendar) cal.clone();
						notification.add(Calendar.DATE, -1*notificationDays);
						Event ev = new Event(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), description, notification, Person.currentPerson);
						try {
							ll.updateEvent(oldEvent, ev);
						} catch (LogicLayerException e1) {
							new ErrorWindow("Unable to update given event");
						}						

						ui.updateEventList(ll);
						
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}
}

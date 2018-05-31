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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class AddNewEventWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextArea textArea;
	JSpinner spinner;


	
	public AddNewEventWindow(LogicLayer ll, Calendar cal, JTextArea textField, Person currentPerson) {
		setBounds(100, 100, 450, 300);
		this.setTitle("Add Event");
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String description = textArea.getText();
						Date time =  (Date) spinner.getValue();
						Event ev = new Event(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), time.getHours(), time.getMinutes(), description, Person.currentPerson);
						ll.createEvent(ev);						

		                textField.setText(ll.getDayDescription(cal));

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

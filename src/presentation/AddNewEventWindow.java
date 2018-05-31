package presentation;
import logic.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Event;
import data.Person;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class AddNewEventWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextArea textArea;
	private JComboBox hourBox;
	private JComboBox minuteBox;

	
	public AddNewEventWindow(LogicLayer ll, Calendar cal, JTextArea textField, Person currentPerson) {
		setBounds(100, 100, 450, 300);
		this.setTitle("Add Event");
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.NORTH);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{106, 32, 72, 60, 0};
			gbl_panel.rowHeights = new int[]{25, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel label = new JLabel("Event time:");
				label.setFont(new Font("Tahoma", Font.PLAIN, 20));
				label.setEnabled(false);
				GridBagConstraints gbc_label = new GridBagConstraints();
				gbc_label.gridwidth = 2;
				gbc_label.anchor = GridBagConstraints.NORTHWEST;
				gbc_label.insets = new Insets(0, 0, 0, 5);
				gbc_label.gridx = 0;
				gbc_label.gridy = 0;
				panel.add(label, gbc_label);
			}
			{
				String[] hour = new String[24];
				for(int i=0; i<24; i++) {
					hour[i]=Integer.toString(i);
				}
				
				hourBox = new JComboBox(hour);
				GridBagConstraints gbc_hourBox = new GridBagConstraints();
				gbc_hourBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_hourBox.insets = new Insets(0, 0, 0, 5);
				gbc_hourBox.gridx = 2;
				gbc_hourBox.gridy = 0;
				panel.add(hourBox, gbc_hourBox);
			}
			{
				String[] minute = new String[60];
				for(int i=0; i<60; i++) {
					minute[i]=Integer.toString(i);
				}
				
				minuteBox = new JComboBox(minute);
				GridBagConstraints gbc_minuteBox = new GridBagConstraints();
				gbc_minuteBox.fill = GridBagConstraints.HORIZONTAL;
				gbc_minuteBox.gridx = 3;
				gbc_minuteBox.gridy = 0;
				panel.add(minuteBox, gbc_minuteBox);
			}
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
						int hour = hourBox.getSelectedIndex();
						int minutes = minuteBox.getSelectedIndex();
						Event ev = new Event(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), hour, minutes, description, Person.currentPerson);
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

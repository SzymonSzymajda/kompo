package presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Person;
import logic.LogicLayer;
import logic.LogicLayerException;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class SettingsWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox;
	private Person cp;

	public SettingsWindow(LogicLayer ll, Person currentPerson) {
		cp = currentPerson;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 234, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCurrentPerson = new JLabel("Current person");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(this.getComboList(ll)));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] selectedPerson = comboBox.getSelectedItem().toString().split(" ");					
					cp = ll.getPerson(selectedPerson[0], selectedPerson[1]);
					Person.currentPerson = cp;
				} catch (LogicLayerException e1) {
					e1.printStackTrace();
				}
			}
		});

		
		JButton btnAddNewPerson = new JButton("Add new person");
		btnAddNewPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewPersonWindow(ll, comboBox);
			}
		});
		
		JButton btnRemovePerson = new JButton("Remove person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] selectedPerson = comboBox.getSelectedItem().toString().split(" ");
					ll.deletePerson(selectedPerson[0], selectedPerson[1]);
				} catch (LogicLayerException e1) {
					e1.printStackTrace();
				}
				comboBox.setModel(new DefaultComboBoxModel<String>(getComboList(ll)));
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCurrentPerson, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAddNewPerson)
						.addComponent(btnRemovePerson))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentPerson)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddNewPerson)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(btnRemovePerson))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}
	
	Vector<String> getComboList(LogicLayer ll) {
		Vector<String> ret =  new Vector<String>();
		for(Person p : ll.getAllPeople()) {
			ret.add(p.getName() + " " + p.getSurname());
		}
		return ret;
	}
}

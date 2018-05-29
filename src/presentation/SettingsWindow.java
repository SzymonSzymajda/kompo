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
	Person cp;

	public SettingsWindow(LogicLayer ll, Person currentPerson) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 234, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblCurrentPerson = new JLabel("Current person");
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(this.getComboList(ll)));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = comboBox.getSelectedIndex();
				try {
					cp = ll.getPerson(id);
				} catch (LogicLayerException e1) {
					// TODO Auto-generated catch block
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
					ll.deletePerson(comboBox.getSelectedIndex());
				} catch (LogicLayerException e1) {
					// TODO Auto-generated catch block
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
						disposeAndChangeCurrentPerson(currentPerson);
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
		for(Person p : ll.getAllPeople().values()) {
			ret.add(p.getName() + " " + p.getSurname());
		}
		return ret;
	}
	
	
	void disposeAndChangeCurrentPerson(Person currentPerson) {
	    currentPerson = this.cp;
	    super.dispose();
	}
}

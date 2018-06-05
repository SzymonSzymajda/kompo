package presentation;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.LogicLayer;
import logic.LogicLayerException;
import logic.Serializer;
import logic.XMLSerializer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

/**
 * Dialog for loading data from xml 
 */
@SuppressWarnings("serial")
public class LoadFromXmlWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public LoadFromXmlWindow(LogicLayer ll) {
		setBounds(100, 100, 211, 132);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Load from XML");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JButton btnSave = new JButton("Load");
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = textField.getText();
				try {
					Serializer s = new XMLSerializer();
					ll.loadDataService(s.deserialize(filename + ".xml"));
				} catch (LogicLayerException e1) {
					new ErrorWindow(e1);
				}
				dispose();
			}
		});
		
		JLabel lblFileName = new JLabel("File name");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFileName)
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSave)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFileName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave)
					.addGap(155))
		);
		contentPanel.setLayout(gl_contentPanel);
		setVisible(true);
	}
}

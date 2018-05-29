package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;

public class ErrorWindow extends JDialog{
	
	public ErrorWindow(Exception e) {
		setTitle("Error");
		setBounds(100, 100, 211, 132);
		setResizable(false);
		this.setLocationRelativeTo(null);
		
		JLabel lblDescription = new JLabel(e.getMessage());
		lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(74)
					.addComponent(lblDescription, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(70))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		this.setVisible(true);
		this.requestFocus();
	}

	
}

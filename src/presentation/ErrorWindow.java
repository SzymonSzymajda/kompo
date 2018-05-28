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

public class ErrorWindow extends JDialog{
	
	public ErrorWindow(Exception e) {
		setTitle("Error");
		setBounds(100, 100, 211, 132);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblDescription = new JLabel(e.getMessage());
		lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
		getContentPane().add(lblDescription);
		this.setVisible(true);
	}

	
}

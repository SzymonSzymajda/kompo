package presentation;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;

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

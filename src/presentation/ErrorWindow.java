package presentation;

import javax.swing.JOptionPane;

import javax.swing.JDialog;

@SuppressWarnings("serial")
public class ErrorWindow extends JDialog{
	
	public ErrorWindow(Exception e) {		
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
}

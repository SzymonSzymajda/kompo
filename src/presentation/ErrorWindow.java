package presentation;

import javax.swing.JOptionPane;

import javax.swing.JDialog;

/**
 * Dialog for showing errors and warnings
 */
@SuppressWarnings("serial")
public class ErrorWindow extends JDialog{
	
	public ErrorWindow(Exception e) {		
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public ErrorWindow(String str) {
		JOptionPane.showMessageDialog(null, str, "Error", JOptionPane.ERROR_MESSAGE);
	}
}

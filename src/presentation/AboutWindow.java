package presentation;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * Dialog with information about the application
 */
@SuppressWarnings("serial")
public class AboutWindow extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextArea txtAuthors;

	
	public AboutWindow() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		setTitle("About");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			txtAuthors = new JTextArea();
			//txtAuthors.setHorizontalAlignment(SwingConstants.CENTER);
			txtAuthors.setEditable(false);
			txtAuthors.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtAuthors.setText("Shortsight\nVersion: 1.0\nAuthors: Szymon Szymajda, Pawe³ Galewicz\nDescription: Calendar application");
			contentPanel.add(txtAuthors);
			txtAuthors.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
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

}

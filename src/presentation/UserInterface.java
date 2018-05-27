package presentation;
import logic.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Component;

@SuppressWarnings("serial")
public class UserInterface extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model;
	JLabel label;
	JTextArea textField;
	Calendar temp = Calendar.getInstance();
	Calendar cal = new GregorianCalendar();

	public UserInterface(LogicLayer ll) {
		
		try {
			ll.loadDataService(XMLSerializer.importData("autosave.xml"));
		} catch (LogicLayerException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calendar");
		setBounds(100, 100, 864, 316);
		
		addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                XMLSerializer.save("autosave.xml", ll.getDataService());
                e.getWindow().dispose();
            }
        });
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMore = new JMenu("More");
		mnMore.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(mnMore);
		
		JMenuItem mntmSettings = new JMenuItem("Settings");
		mnMore.add(mntmSettings);
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsWindow();
			}
		});
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnMore.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutWindow();
			}
		});
		
		JMenu mnSave = new JMenu("Save");
		mnMore.add(mnSave);
		
		JMenuItem mntmSaveToXml = new JMenuItem("Save to XML");
		mnSave.add(mntmSaveToXml);
		mntmSaveToXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveToXmlWindow(ll.getDataService());
			}
		});
		
		JMenu mnLoad = new JMenu("Load");
		mnMore.add(mnLoad);
		
		JMenuItem mntmLoadFromXml = new JMenuItem("Load from XML");
		mnLoad.add(mntmLoadFromXml);
		mntmLoadFromXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoadFromXmlWindow(ll);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton button_1 = new JButton("->");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, +1);
		        updateMonth();
			}
		});
		panel_1.add(button_1, BorderLayout.EAST);
		
		JButton button = new JButton("<-");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, -1);
		        updateMonth();
			}
		});
		panel_1.add(button, BorderLayout.WEST);
		
		label = new JLabel();
		panel_1.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
				
		String [] columns = {"Sun", "Mon","Tue","Wed","Thu","Fri","Sat"};
	    model = new DefaultTableModel(null,columns);
	    JTable table = new JTable(model);
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    	
	    		int row = table.rowAtPoint(e.getPoint());
	            int col = table.columnAtPoint(e.getPoint());
	            if (row >= 0 && col >= 0) {
	                int day = (int)table.getModel().getValueAt(row, col);
	                temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), day);
	                ArrayList<Event> events = ll.getAllEventsFrom(temp);
	                if(events.size()==0) {
	                	textField.setText("Month: " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "\nDay: " + day);
	                }
	                else {
		                String description = "";
		                int number = 0;
	                	for(Event event : events) {
		                	description += "#" + (++number) + "\n" + event.toString() + "\n";
		                }
		                textField.setText(description);	  
	                }
	                              
	            }
	    	}
	    });
	    JScrollPane panel_2 = new JScrollPane(table);
		panel.add(panel_2, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.SOUTH);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddNewEventWindow(ll, temp); 
			}
		});
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                new DeleteEventWindow(ll, temp);
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addComponent(btnAddEvent)
					.addPreferredGap(ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
					.addComponent(btnDeleteEvent))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDeleteEvent)
						.addComponent(btnAddEvent)))
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		textField = new JTextArea();
		scrollPane.setViewportView(textField);
		textField.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textField.setEnabled(false);
		textField.setText("Month: " + "\nDay: ");
		
		this.updateMonth();
		
		this.setVisible(true);
	}
	
	void updateMonth() {
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	 
	    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	    int year = cal.get(Calendar.YEAR);
	    label.setText(month + " " + year);
	 
	    int startDay = cal.get(Calendar.DAY_OF_WEEK);
	    int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	    int weeks = (int) Math.ceil(( (float) numberOfDays + ( (float) startDay - 1)) / 7);

	 
	    model.setRowCount(0);
	    model.setRowCount(weeks);
	 
	    int i = startDay-1;
	    for(int day=1;day<=numberOfDays;day++){
	      model.setValueAt(day, i/7 , i%7 );    
	      i = i + 1;
	    }
	 
	  }
}

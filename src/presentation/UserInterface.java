package presentation;
import logic.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import data.Event;
import data.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.awt.FlowLayout;

/**
 * Singleton containing the main application view
 */
@SuppressWarnings("serial")
public class UserInterface extends JFrame {

	private static JPanel contentPane;
	private DefaultTableModel model;
	private JLabel label;
	private Calendar temp = Calendar.getInstance();
	private Calendar cal = new GregorianCalendar();
	private static volatile UserInterface instance = null;
	private JList<Event> list = new JList<Event>();
	
	/**
	 * If necessary creates the instance of the EventListWindow and shows it
	 * @param ll Current LogicLayer
	 */
	public static void getInstance(LogicLayer ll) {
		if(instance == null) {
			instance = new UserInterface(ll);
		}
		else {
			instance.setVisible(true);
			instance.requestFocus();
		}
	}

	public UserInterface(LogicLayer ll) {
		
		
		Settings.getInstance().init(this, ll);
		
		Settings.getInstance().menuInit(this, contentPane, ll);
		
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
	    model = new DefaultTableModel(null,columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };
	    JTable table = new JTable(model);
	    table.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		int row = table.rowAtPoint(e.getPoint());
	            int col = table.columnAtPoint(e.getPoint());
	            
	            if(table.getModel().getValueAt(row, col) == null)
	            	return;
	            	
	            if (row >= 0 && col >= 0) {
	                int day = (int)table.getModel().getValueAt(row, col);
	                temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), day);
	                
	                updateEventList(ll);
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
				if(Person.currentPerson == null) {
					new ErrorWindow("No user selected");
				}
				else {
					new AddNewEventWindow(ll, temp, instance);
				}
			}
		});
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(btnAddEvent);
		
		JPanel panel_4 = new JPanel();
		splitPane.setRightComponent(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollList = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		list.setCellRenderer(new Settings.MyCellRenderer(290));

		panel_4.add(scrollList);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblEvents = new JLabel("Events");
		panel_5.add(lblEvents);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.SOUTH);
		
		JButton btnEditEvent = new JButton("Edit Event");
		btnEditEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ll.deleteEvent(list.getSelectedValue());
					new AddNewEventWindow(ll, temp, instance);
					instance.updateEventList(ll);
				} catch (LogicLayerException e) {
					new ErrorWindow("No event chosen");
				}
				
			}
		});
		panel_6.add(btnEditEvent);
		
		JButton btnDeleteEvent = new JButton("Delete Event");
		panel_6.add(btnDeleteEvent);
		btnDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ll.deleteEvent(list.getSelectedValue());
					instance.updateEventList(ll);
				} catch (LogicLayerException e) {
					new ErrorWindow("No event chosen");
				}
			}
		});
		
		this.updateMonth();
		
		contentPane.setBackground(Settings.getInstance().backgroundColor);
		
		this.setVisible(true);
		new UserSelectionWindow(ll);
	}
	
	/**
	 * Updates the list of events showed in the UserInterface frame
	 * @param ll Current LogicLayer
	 */
	public void updateEventList(LogicLayer ll) {
		DefaultListModel<Event> dlm = new DefaultListModel<Event>();
        for(Event item: ll.getAllEventsFrom(temp)) {
        	dlm.addElement(item);
        }
        list.setModel(dlm);	
	}

	/**
	 * Updates the calendar in the UserInterface frame
	 */
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

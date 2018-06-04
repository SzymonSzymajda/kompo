package presentation;
import logic.*;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JList;

@SuppressWarnings("serial")
public class EventListWindow extends JFrame {

	private static JPanel contentPane;
	private DefaultTableModel model;
	private Calendar cal = new GregorianCalendar();
	private static volatile EventListWindow instance = null;
	
	public static void getInstance(LogicLayer ll) {
		if(instance == null) {
			instance = new EventListWindow(ll);
		}
		else {
			instance.setVisible(true);
			instance.requestFocus();
		}
	}

	public EventListWindow(LogicLayer ll) {
		Settings.getInstance().menuInit(this, contentPane, ll);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		String[] s = new String[10];
		
		for(int i=0; i<10; i++) {
			s[i]=Integer.toString(i);
		}
		
		
		JList<String> list = new JList<String>(s);
		contentPane.add(list, BorderLayout.NORTH);
				
		String [] columns = {"Sun", "Mon","Tue","Wed","Thu","Fri","Sat"};
	    model = new DefaultTableModel(null,columns) {
	    	@Override
	    	public boolean isCellEditable(int row, int column) {
	    		return false;
	    	}
	    };
		
		this.updateMonth();
		
		this.setVisible(true);
	}
	
	void updateMonth() {
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	 
	   // String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	    //int year = cal.get(Calendar.YEAR);
	 
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

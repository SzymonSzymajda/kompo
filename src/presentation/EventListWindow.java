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
public class EventListWindow extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private Calendar temp = Calendar.getInstance();
	private Calendar cal = new GregorianCalendar();
	private Person currentPerson;
	private static volatile EventListWindow instance = null;
	
	public static void getInstance(LogicLayer ll) {
		if(instance == null) {
			instance = new EventListWindow(ll);
			System.out.println("simea");
		}
		else {
			instance.setVisible(true);
			instance.requestFocus();
		}
	}

	public EventListWindow(LogicLayer ll) {
		
		try {
			ll.loadDataService(XMLSerializer.importData("autosave.xml"));
		} catch (LogicLayerException e2) {
			// TODO Auto-generated catch block
			new ErrorWindow(e2);
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
		setBounds(100, 100, 400, 316);
		
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
				new SettingsWindow(ll, currentPerson);
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
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmCalendar = new JMenuItem("Calendar");
		mntmCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instance.setVisible(false);
				UserInterface.getInstance(ll);
			}
		});
		mnView.add(mntmCalendar);
		
		JMenuItem mntmListOfEvents = new JMenuItem("List of events");
		mnView.add(mntmListOfEvents);
		mntmLoadFromXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoadFromXmlWindow(ll);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		String[] s = new String[10];
		
		for(int i=0; i<10; i++) {
			s[i]=Integer.toString(i);
		}
		
		
		JList list = new JList(s);
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
	 
	    String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	    int year = cal.get(Calendar.YEAR);
	 
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

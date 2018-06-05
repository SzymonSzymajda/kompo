package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import logic.LogicLayer;
import logic.LogicLayerException;
import logic.OpenOfficeSaver;
import logic.Serializer;
import logic.XMLSerializer;


/**
 * Class containing all the application settings and methods for initialization
 */
public class Settings {
	
	public static Settings instance = null;
	public Color backgroundColor;
	public boolean autosaveToXML;
	public transient boolean database;
	
	
	/**
	 * Creates the Settings object
	 */
	private Settings() {
		backgroundColor = null;
		autosaveToXML = true;
		database = false;
	}
	
	
	/**
	 * If necessary creates and returns the instance of the Settings singleton
	 * @return instance of the Settings singleton
	 */
	public static Settings getInstance() {
		if(instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
	/**
	 * Exports current settings into the settings.xml file
	 */
	public void saveSettings() {
		
		XStream xstream = new XStream(new StaxDriver());
		try {
			xstream.toXML(Settings.getInstance(),new FileWriter("settings.xml"));
		} catch (IOException e) {
			new ErrorWindow(e);
		}
	}
	
	/**
	 * Imports settings into the settings.xml file
	 */
	public void loadSettings() {
		XStream xstream = new XStream(new StaxDriver());
		FileInputStream fileIn = null;
		try {
			fileIn = new FileInputStream("settings.xml");
			instance = (Settings)xstream.fromXML(fileIn);
			fileIn.close();
		} catch (Exception e) {
			new ErrorWindow("Unable to load previous settings");
		} finally {
			//System.out.println(Settings.getInstance().backgroundColor);
			//System.out.println(Settings.getInstance().autosave);
		}
	}
	
	/**
	 * Loads settings; if xml autosave is enabled, imports data from autosave.xml and applies some essential settings to the frame
	 * @param frame the frame for which the settings are to be applied
	 * @param ll current LogicLayer
	 */
	public void init(JFrame frame, LogicLayer ll) {
		
		loadSettings();
		
		if(Settings.getInstance().autosaveToXML) {
			try {
				Serializer s = new XMLSerializer();
				ll.loadDataService(s.deserialize("autosave.xml"));
			} catch (LogicLayerException e2) {
				new ErrorWindow(new LogicLayerException("No autosave"));
			}
		}
		
		Calendar cutoff = Calendar.getInstance();
		cutoff.add(Calendar.MONTH, -1);
		ll.removeOldEvents(cutoff);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calendar");
		frame.setBounds(100, 100, 864, 316);
		
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
    			saveSettings();
            	if(Settings.getInstance().autosaveToXML) {
            		try {
            			Serializer s = new XMLSerializer();
						s.serialize("autosave.xml", ll.getDataService());
						new OpenOfficeSaver().save("luj.odt", ll.getDataService());
					} catch (LogicLayerException e1) {
						new ErrorWindow(e1);
					}
            	}
                e.getWindow().dispose();
            }
        });
	}

	/**
	 * Creates menu in the given frame
	 * @param frame the frame in which the menu is to be initialized
	 * @param cp contentPane
	 * @param ll curreent LogicLayer
	 */
	public void menuInit(JFrame frame, JPanel cp, LogicLayer ll) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnMore = new JMenu("More");
		mnMore.setAlignmentX(Component.LEFT_ALIGNMENT);
		menuBar.add(mnMore);
		
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
		
		JMenu mnSettings = new JMenu("Settings");
		mnMore.add(mnSettings);
		
		JMenuItem mntmSettings = new JMenuItem("Change user");
		mnSettings.add(mntmSettings);
		
		JMenuItem mntmChangeBackgroundColor = new JMenuItem("Change background color");
		mntmChangeBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color c = JColorChooser.showDialog(frame,
						"Choose a color", Settings.getInstance().backgroundColor);
				if (c != null) Settings.getInstance().backgroundColor = c;
			}
		});
		mnSettings.add(mntmChangeBackgroundColor);
		
		JMenuItem mntmAutosave = new JMenuItem("Autosave");
		mntmAutosave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(frame, "Do you want to enable autosaving");
				if(result == 0) {
					Settings.getInstance().autosaveToXML = true;
				}else if(result == 1) {
					Settings.getInstance().autosaveToXML = false;
				}
			}
		});
		mnSettings.add(mntmAutosave);
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserSelectionWindow(ll);
			}
		});
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmCalendar = new JMenuItem("Calendar");
		mntmCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInterface.getInstance(ll);
			}
		});
		mnView.add(mntmCalendar);
		
		JMenuItem mntmListOfEvents = new JMenuItem("Notifications");
		mntmListOfEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventListWindow.getInstance(ll);
			}
		});
		mnView.add(mntmListOfEvents);
		mntmLoadFromXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoadFromXmlWindow(ll);
			}
		});
	}
	
	/**
	 * Custom renderer for JList with changeable width and a wrapping text
	 */
	@SuppressWarnings("serial")
	static class MyCellRenderer extends DefaultListCellRenderer {
	
		public static final String HTML_1 = "<html><body style='width: ";
		public static final String HTML_2 = "px'>";
		public static final String HTML_3 = "</html>";
		private int width;
	
		/**
		 * Creates the MyCellRenderer object with a given width
		 * @param width
		 */
		public MyCellRenderer(int width) {
			this.width = width;
		}
	
		@SuppressWarnings("rawtypes")
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
														int index, boolean isSelected, 
														boolean cellHasFocus) {
			String text = HTML_1 + String.valueOf(width) + HTML_2 + value.toString() + HTML_3;
			return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
	   }
	
	}

}

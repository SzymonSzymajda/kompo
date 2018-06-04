package presentation;
import logic.*;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.Event;

import java.util.Calendar;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class EventListWindow extends JFrame {

	private static JPanel contentPane;
	private static volatile EventListWindow instance = null;
	private JList<Notif> list;
	
	public static void getInstance(LogicLayer ll) {
		if(instance == null) {
			instance = new EventListWindow(ll);
		}
		else {
			instance.updateNotificationList(ll);
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
		
		JPanel panel = new JPanel();
		this.setSize(300, 400);
		this.setResizable(false);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblThisWeeksNotifications = new JLabel("This weeks notifications");
		panel.add(lblThisWeeksNotifications);
		
		list = new JList<Notif>();
		list.setCellRenderer(new Settings.MyCellRenderer(290));

		updateNotificationList(ll);

		JScrollPane scrollPane = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void updateNotificationList(LogicLayer ll) {
		DefaultListModel<Notif> dlm = new DefaultListModel<Notif>();
        for(Event item: ll.getNotifications(Calendar.getInstance())) {
        	dlm.addElement(new Notif(item));
        }
        list.setModel(dlm);
		
	}
	
	class Notif extends Event{
		public Notif(Event item) {
			Description = item.getDescription();
			EventDate = item.getEventDateCal();
		}

		@Override
		public String toString() {
			SimpleDateFormat time = new SimpleDateFormat("H:m");
			SimpleDateFormat date = new SimpleDateFormat("d.M");

			String ret = "";
			ret = "Date: " + date.format(EventDate.getTime()) + " | Time: " + time.format(EventDate.getTime()) + " | Description: " + Description;
			return ret;
		}
	}
	
}

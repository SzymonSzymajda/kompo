package data;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	protected String Description;
	protected Calendar EventDate;
	private Person Owner;
	private Calendar Notification = null;
	
	public Event() {}
	
	public Event(int year, int month, int day, int hour, int minutes, String description, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.set(year, month, day, hour, minutes);
		Description = description;
		this.Owner = owner;
	}
	
	public Event(Date date, String description, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.setTime(date);
		Description = description;
		this.Owner = owner;
	}
	
	public Event(int year, int month, int day, int hour, int minutes, String description, Calendar notification, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.set(year, month, day, hour, minutes);
		Description = description;
		this.Owner = owner;
		Notification = notification;
	}
	
	public Event(Date date, String description, Calendar notification, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.setTime(date);
		Description = description;
		this.Owner = owner;
		Notification = notification;
	}


	/**
	 * @return
	 */
	public Date getEventDate() {
		return EventDate.getTime();
	}
	
	/**
	 * @return
	 */
	public Calendar getEventDateCal() {
		return EventDate;
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 */
	public void setEventDate(int year, int month, int day) {
		EventDate.set(year, month, day);
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * @return
	 */
	public Person getOwner() {
		return Owner;
	}
	
	/**
	 * @return
	 */
	public Calendar getNotification() {
		return Notification;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		Description = description;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		SimpleDateFormat ft = new SimpleDateFormat("H:m");

		String ret = "";
		ret = "Time: " + ft.format(EventDate.getTime()) + " | Description: " + Description;
		return ret;
	}
}

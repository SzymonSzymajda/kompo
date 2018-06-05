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
	
	/**
	 * Creates an Event object with the given parameters
	 * @param year year of the event
	 * @param month month of the event
	 * @param day day of the event
	 * @param hour hour of the event
	 * @param minutes minutes of the event
	 * @param description description of the event
	 * @param owner owner of the event
	 */
	public Event(int year, int month, int day, int hour, int minutes, String description, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.set(year, month, day, hour, minutes);
		Description = description;
		this.Owner = owner;
	}
	
	/**
	 * Creates an Event object with the given parameters
	 * @param date date of the event
	 * @param description description of the event
	 * @param owner owner of the event
	 */
	public Event(Date date, String description, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.setTime(date);
		Description = description;
		this.Owner = owner;
	}
	
	/**
	 * Creates an Event object with the given parameters
	 * @param year year of the event
	 * @param month month of the event
	 * @param day day of the event
	 * @param hour hour of the event
	 * @param minutes minutes of the event
	 * @param description description of the event
	 * @param notification Notification object linked to the event
	 * @param owner owner of the event
	 */
	public Event(int year, int month, int day, int hour, int minutes, String description, Calendar notification, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.set(year, month, day, hour, minutes);
		Description = description;
		Owner = owner;
		Notification = notification;
	}
	
	/**
	 * Creates an Event object with the given parameters
	 * @param date date of the event
	 * @param description description of the event
	 * @param notification Notification object linked to the event
	 * @param owner owner of the event
	 */
	public Event(Date date, String description, Calendar notification, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.setTime(date);
		Description = description;
		this.Owner = owner;
		Notification = notification;
	}


	/**
	 * Returns the date of the Event
	 * @return date of the event as a Date object
	 */
	public Date getEventDate() {
		return EventDate.getTime();
	}
	
	/**
	 * Returns the date of the Event
	 * @return date of the event as a Calendar object
	 */
	public Calendar getEventDateCal() {
		return EventDate;
	}

	/**
	 * Sets the date of the Event with the given parameters
	 * @param year year of the event
	 * @param month month of the event
	 * @param day day of the event
	 */
	public void setEventDate(int year, int month, int day) {
		EventDate.set(year, month, day);
	}

	/**
	 * Returns the description of the Event
	 * @return String containing description of the event
	 */
	public String getDescription() {
		return Description;
	}
	
	/**
	 * Returns the owner of the Event
	 * @return Person object linked the event
	 */
	public Person getOwner() {
		return Owner;
	}
	
	/**
	 * Returns the notification date of the Event
	 * @return Notification object linked to the event
	 */
	public Calendar getNotification() {
		return Notification;
	}

	/**
	 * Sets the description of the Event with the given String
	 * @param description String containing description description of the event
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

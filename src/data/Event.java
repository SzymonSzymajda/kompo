package data;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String Description;
	private Calendar EventDate;
	private Person Owner;
	
	public Event() {}
	
	public Event(int year, int month, int day, int hour, int minutes, String description, Person owner) {
		super();
		EventDate = GregorianCalendar.getInstance();
		EventDate.set(year, month, day, hour, minutes);
		Description = description;
		this.Owner = owner;
	}

	public Date getEventDate() {
		return EventDate.getTime();
	}
	
	public Calendar getEventDateCal() {
		return EventDate;
	}

	public void setEventDate(int year, int month, int day) {
		EventDate.set(year, month, day);
	}

	public String getDescription() {
		return Description;
	}
	
	public Person getOwner() {
		return Owner;
	}

	public void setDescription(String description) {
		Description = description;
	}


	@Override
	public String toString() {
		SimpleDateFormat ft = new SimpleDateFormat("EEEE, d MMMM y 'o' H:m");

		String ret = "";
		ret = "Date: " + ft.format(EventDate.getTime()) + "\nDescription: " + Description;
		return ret;
	}
}

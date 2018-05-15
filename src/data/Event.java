package data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class Event {
	
	private String Description;
	private HashSet<Person> ConnectedPeople = new HashSet<Person>();
	private Calendar EventDate;
	
	public Event(int year, int month, int day, String description) {
		super();
		EventDate = Calendar.getInstance();
		EventDate.set(year, month, day);
		Description = description;
	}

	public Date getEventDate() {
		return EventDate.getTime();
	}

	public void setEventDate(int year, int month, int day) {
		EventDate.set(year, month, day);
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public HashSet<Person> getConnectedPeople() {
		return ConnectedPeople;
	}

	public void addConnectedPerson(Person person) {
		ConnectedPeople.add(person);
	}

	@Override
	public String toString() {
		SimpleDateFormat ft = new SimpleDateFormat("EEEE, d MMMM y 'o' H:m");
		String ret = "Date: " + ft.format(EventDate.getTime()) + "\nDescription: " + Description + "\nWith:\n";
		for(Person person: ConnectedPeople) {
			ret += "   *" + person.toString() + "\n";
		}
		return ret;
	}	
}

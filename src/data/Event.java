package data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Event {
	private Date EventDate;
	private String Description;
	private HashSet<Person> ConnectedPeople = new HashSet<Person>();
	
	public Event(Date eventDate, String description) {
		super();
		EventDate = eventDate;
		Description = description;
	}

	public Date getEventDate() {
		return EventDate;
	}

	public void setEventDate(Date eventDate) {
		EventDate = eventDate;
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
		String ret = "Date: " + ft.format(EventDate) + "\nDescription: " + Description + "\nWith:\n";
		for(Person person: ConnectedPeople) {
			ret += "   *" + person.toString() + "\n";
		}
		return ret;
	}
	
	
	
	
}

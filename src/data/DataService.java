package data;

import java.util.HashSet;
import java.util.TreeMap;

public class DataService {
	
	private DataContext data = new DataContext();
	
	private int peopleCounter = 0;
	private int eventCounter = 0;
	
	//CRUD Person
	public void createPerson(Person p) {
		data.People.put(peopleCounter, p);
		peopleCounter++;
	}
	public Person getPerson(int id) {
		if(data.People.containsKey(id)) {
			return data.People.get(id);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}
		return null;
	}
	public void updatePerson(int id, Person p) {
		if(data.People.containsKey(id)) {
			data.People.put(id, p);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}			
	}
	public void deletePerson(int id) {
		if(data.People.containsKey(id)) {
			data.People.remove(id);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void deletePerson(Person p) {
		if(data.People.containsValue(p)) {
			data.People.values().remove(p);
		}
		else {
			//throw new DataServiceException("Given Person is not mapped");
		}
	}
	public TreeMap<Integer, Person> getAllPeople(){
		return data.People;
	}
	
	//CRUD Event
	public void createEvent(Event e) {
		data.Events.put(eventCounter, e);
		eventCounter++;
	}
	public Event getEvent(int id) {
		if(data.Events.containsKey(id)) {
			return data.Events.get(id);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}
		return null;
	}
	public void updateEvent(int id, Event e) {
		if(data.Events.containsKey(id)) {
			data.Events.put(id, e);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}			
	}
	public void deleteEvent(int id)  {
		if(data.Events.containsKey(id)) {
			data.Events.remove(id);
		}
		else {
			//throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void deleteEvent(Event e)  {
		if(data.Events.containsValue(e)) {
			data.Events.values().remove(e);
		}
		else {
			//throw new DataServiceException("Given Event is not mapped");
		}
	}
	public TreeMap<Integer, Event> getAllEvents(){
		return data.Events;
	}
	public void addPeopleToEvent(int eventId, Person...persons) {
		HashSet<Person> set = new HashSet<Person>();
		for(Person p : persons) {
			set.add(p);
		}
		data.Events.get(eventId).setConnectedPeople(set);
	}
}

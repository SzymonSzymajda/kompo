package data;

import java.util.TreeMap;

public class DataService {
	
	private DataContext data = new DataContext();
	
	private int pCounter = 0;
	private int eCounter = 0;
	
	//CRUD Person
	public void createPerson(Person p) {
		data.People.put(pCounter, p);
		pCounter++;
	}
	public void getPerson(int id) {
		data.People.get(id);
	}
	public void updatePerson(int id, Person p) {
		if(data.People.containsKey(id)) {
			data.People.put(id, p);
		}
		else {
			//exception
		}			
	}
	public void deletePerson(int id) {
		if(data.People.containsKey(id)) {
			data.People.remove(id);
		}
		else {
			//exception
		}
	}
	public void deletePerson(Person p) {
		if(data.People.containsValue(p)) {
			data.People.values().remove(p);
		}
		else {
			//exception
		}
	}
	public TreeMap<Integer, Person> getAllPeople(){
		return data.People;
	}
	
	//CRUD Event
	public void createEvent(Event e) {
		data.Events.put(eCounter, e);
		eCounter++;
	}
	public void getEvent(int id) {
		if(data.Events.containsKey(id)) {
			data.Events.get(id);
		}
		else {
			//exception
		}
	}
	public void updateEvent(int id, Event e) {
		if(data.Events.containsKey(id)) {
			data.Events.put(id, e);
		}
		else {
			//exception
		}			
	}
	public void deleteEvent(int id) {
		if(data.Events.containsKey(id)) {
			data.Events.remove(id);
		}
		else {
			//exception
		}
	}
	public void deleteEvent(Event e) {
		if(data.Events.containsValue(e)) {
			data.Events.values().remove(e);
		}
		else {
			//exception
		}
	}
	public TreeMap<Integer, Event> getAllEvents(){
		return data.Events;
	}
}

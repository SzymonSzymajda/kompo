package data;

import java.util.ArrayList;
import java.util.TreeMap;

public class DataService {
	
	private DataContext Data = new DataContext();
	
	private int eventCounter = 0;
	
	//CRUD Person
	public void createPerson(Person p) {
		Data.People.add(p);
	}
	public Person getPerson(String name, String surname) throws DataServiceException {
		for(Person p : Data.People) {
			if(p.getName().equals(name) && p.getSurname().equals(surname)) {
				return p;
			}
		}
		throw new DataServiceException("Specified person does not exist");
	}
	/*
	public void updatePerson(int id, Person p) throws DataServiceException {
		if(Data.People.containsKey(id)) {
			Data.People.put(id, p);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}			
	}*/
	public void deletePerson(String name, String surname) throws DataServiceException {
		for(Person p : Data.People) {
			if(p.getName().equals(name) && p.getSurname().equals(surname)) {
				Data.People.remove(p);
			}
		}
		throw new DataServiceException("Specified person does not exist");
	}
	public void deletePerson(Person p) throws DataServiceException {
		if(Data.People.contains(p)) {
			Data.People.remove(p);
		}
		else {
			throw new DataServiceException("Specified person does not exist");
		}
	}
	public ArrayList<Person> getAllPeople(){
		return Data.People;
	}
	
	//CRUD Event
	public void createEvent(Event e) {
		Data.Events.put(eventCounter, e);
		eventCounter++;
	}
	public Event getEvent(int id) throws DataServiceException {
		if(Data.Events.containsKey(id)) {
			return Data.Events.get(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void updateEvent(int id, Event e) throws DataServiceException {
		if(Data.Events.containsKey(id)) {
			Data.Events.put(id, e);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}			
	}
	public void deleteEvent(int id) throws DataServiceException  {
		if(Data.Events.containsKey(id)) {
			Data.Events.remove(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void deleteEvent(Event e) throws DataServiceException  {
		if(Data.Events.containsValue(e)) {
			Data.Events.values().remove(e);
		}
		else {
			throw new DataServiceException("Given Event is not mapped");
		}
	}
	public TreeMap<Integer, Event> getAllEvents(){
		return Data.Events;
	}
}

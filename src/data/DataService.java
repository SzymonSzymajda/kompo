package data;

import java.util.TreeMap;

public class DataService {
	
	private DataContext Data = new DataContext();
	
	private int peopleCounter = 0;
	private int eventCounter = 0;
	
	//CRUD Person
	public void createPerson(Person p) {
		Data.People.put(peopleCounter, p);
		peopleCounter++;
	}
	public Person getPerson(int id) throws DataServiceException {
		if(Data.People.containsKey(id)) {
			return Data.People.get(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void updatePerson(int id, Person p) throws DataServiceException {
		if(Data.People.containsKey(id)) {
			Data.People.put(id, p);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}			
	}
	public void deletePerson(int id) throws DataServiceException {
		if(Data.People.containsKey(id)) {
			Data.People.remove(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	public void deletePerson(Person p) throws DataServiceException {
		if(Data.People.containsValue(p)) {
			Data.People.values().remove(p);
		}
		else {
			throw new DataServiceException("Given Person is not mapped");
		}
	}
	public TreeMap<Integer, Person> getAllPeople(){
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
	
	public void addPeopleToEvent(int eventId, Person...persons) throws DataServiceException {
		for(Person p: persons) {
			this.getEvent(eventId).addConnectedPerson(p);
		}
	}
}

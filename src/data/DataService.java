package data;

import java.util.ArrayList;
import java.util.TreeMap;

public class DataService {
	
	protected DataContext Data = new DataContext();
	
	protected int eventCounter = 0;
	
	/**
	 * @param p Person object being added to database
	 */
	public void createPerson(Person p) {
		Data.People.add(p);
	}
	/**
	 * @param name value of name field of sought Person object
	 * @param surname value of surname field of sought Person object
	 * @return Person object with given name and surname 
	 * @throws DataServiceException if the given Person object with given name and surname does not exist
	 */
	public Person getPerson(String name, String surname) throws DataServiceException {
		for(Person p : Data.People) {
			if(p.getName().equals(name) && p.getSurname().equals(surname)) {
				return p;
			}
		}
		throw new DataServiceException("Specified person does not exist");
	}
	
	/**
	 * @param name value of name field of Person object being removed
	 * @param surname value of surname field of Person object being removed
	 * @throws DataServiceException if the given Person object with given name and surname does not exist
	 */
	public void deletePerson(String name, String surname) throws DataServiceException {
		for(Person p : Data.People) {
			if(p.getName().equals(name) && p.getSurname().equals(surname)) {
				Data.People.remove(p);
			}
		}
		throw new DataServiceException("Specified person does not exist");
	}
	/**
	 * @param p Person object being removed
	 * @throws DataServiceException if given Person object is not mapped
	 */
	public void deletePerson(Person p) throws DataServiceException {
		if(Data.People.contains(p)) {
			Data.People.remove(p);
		}
		else {
			throw new DataServiceException("Specified person does not exist");
		}
	}
	/**
	 * @return array containing all stored Person objects
	 */
	public ArrayList<Person> getAllPeople(){
		return Data.People;
	}
	
	/**
	 * @param e Event object being added to database
	 */
	public void createEvent(Event e) {
		Data.Events.put(eventCounter, e);
		eventCounter++;
	}
	/**
	 * @param id key to which sought Event object is mapped
	 * @return Event object
	 * @throws DataServiceException if given key has no value mapped to it
	 */
	public Event getEvent(int id) throws DataServiceException {
		if(Data.Events.containsKey(id)) {
			return Data.Events.get(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	/**
	 * @param id key to which updated Event object is mapped
	 * @param e new Event object to be mapped under given key
	 * @throws DataServiceException if given key has no value mapped to it
	 */
	public void updateEvent(int id, Event e) throws DataServiceException {
		if(Data.Events.containsKey(id)) {
			Data.Events.put(id, e);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}			
	}
	/**
	 * @param id key to which Event object being removed is mapped
	 * @throws DataServiceException if given key has no value mapped to it
	 */
	public void deleteEvent(int id) throws DataServiceException  {
		if(Data.Events.containsKey(id)) {
			Data.Events.remove(id);
		}
		else {
			throw new DataServiceException("Given ID is not mapped");
		}
	}
	/**
	 * @param e Event object being removed
	 * @throws DataServiceException if given object is not mapped
	 */
	public void deleteEvent(Event e) throws DataServiceException  {
		if(Data.Events.containsValue(e)) {
			Data.Events.values().remove(e);
		}
		else {
			throw new DataServiceException("Given Event is not mapped");
		}
	}
	/**
	 * @return map contaning all stored Event objects
	 */
	public TreeMap<Integer, Event> getAllEvents(){
		return Data.Events;
	}
}

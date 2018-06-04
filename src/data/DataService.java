package data;

import java.util.ArrayList;
import java.util.TreeMap;

public class DataService {
	
	protected DataContext Data = new DataContext();
	
	private int eventCounter = 0;
	
	/**
	 * @param p
	 */
	public void createPerson(Person p) {
		Data.People.add(p);
	}
	/**
	 * @param name
	 * @param surname
	 * @return
	 * @throws DataServiceException
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
	 * @param name
	 * @param surname
	 * @throws DataServiceException
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
	 * @param p
	 * @throws DataServiceException
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
	 * @return
	 */
	public ArrayList<Person> getAllPeople(){
		return Data.People;
	}
	
	/**
	 * @param e
	 */
	public void createEvent(Event e) {
		Data.Events.put(eventCounter, e);
		eventCounter++;
	}
	/**
	 * @param id
	 * @return
	 * @throws DataServiceException
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
	 * @param id
	 * @param e
	 * @throws DataServiceException
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
	 * @param id
	 * @throws DataServiceException
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
	 * @param e
	 * @throws DataServiceException
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
	 * @return
	 */
	public TreeMap<Integer, Event> getAllEvents(){
		return Data.Events;
	}
}

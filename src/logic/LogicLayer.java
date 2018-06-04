package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TreeMap;

import data.DataService;
import data.DataServiceException;
import data.DataServiceSQL;
import data.Event;
import data.Person;

public class LogicLayer {
	
	private DataService Data = new DataServiceSQL();
	
	/**
	 * @param name
	 * @param surname
	 */
	public void createPerson(String name, String surname) {
		Data.createPerson(new Person(name, surname));
	}
	
	/**
	 * @param name
	 * @param surname
	 * @return
	 * @throws LogicLayerException
	 */
	public Person getPerson(String name, String surname) throws LogicLayerException{
		try {
			return Data.getPerson(name, surname);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @param name
	 * @param surname
	 * @throws LogicLayerException
	 */
	public void deletePerson(String name, String surname) throws LogicLayerException {
		try {
			Data.deletePerson(name, surname);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @param p
	 * @throws LogicLayerException
	 */
	public void deletePerson(Person p) throws LogicLayerException {
		try {
			
			Data.deletePerson(p);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @return
	 */
	public ArrayList<Person> getAllPeople(){
		return Data.getAllPeople();
	}
	
	/**
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minutes
	 * @param description
	 * @param owner
	 */
	public void createEvent(int year, int month, int day, int hour, int minutes, String description, Person owner) {
		Data.createEvent(new Event(year, month, day, hour, minutes, description, owner));
	}
	
	/**
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minutes
	 * @param description
	 * @param notification
	 * @param owner
	 */
	public void createEvent(int year, int month, int day, int hour, int minutes, String description, Calendar notification, Person owner) {
		Data.createEvent(new Event(year, month, day, hour, minutes, description, notification, owner));
	}
	
	/**
	 * @param e
	 */
	public void createEvent(Event e){
		Data.createEvent(e);
	}
	
	/**
	 * @param id
	 * @return
	 * @throws LogicLayerException
	 */
	public Event getEvent(int id) throws LogicLayerException {
		try {
			return Data.getEvent(id);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Given ID is not mapped");
		}
	}
	
	/**
	 * @param id
	 * @param e
	 * @throws LogicLayerException
	 */
	public void updateEvent(int id, Event e) throws LogicLayerException {
		try {
			Data.updateEvent(id, e);
		} catch (DataServiceException e1) {
			throw new LogicLayerException("Given ID is not mapped");
		}		
	}
	
	/**
	 * @param id
	 * @throws LogicLayerException
	 */
	public void deleteEvent(int id) throws LogicLayerException {
		try {
			Data.deleteEvent(id);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Given ID is not mapped");
		}
	}
	
	/**
	 * @param e
	 * @throws LogicLayerException
	 */
	public void deleteEvent(Event e) throws LogicLayerException {
		try {
			Data.deleteEvent(e);
		} catch (DataServiceException e1) {
			throw new LogicLayerException("Given ID is not mapped");
		}
	}
	
	/**
	 * @return
	 */
	public TreeMap<Integer, Event> getAllEvents(){
		return Data.getAllEvents();
	}
	
	/**
	 * @param date
	 * @return
	 */
	public ArrayList<Event> getAllEventsFrom(Calendar date){
		ArrayList<Event> ret = new ArrayList<Event>();
		for(Event e : this.getAllEvents().values()) {
			if(		   e.getEventDateCal().get(Calendar.YEAR) == date.get(Calendar.YEAR)
					&& e.getEventDateCal().get(Calendar.MONTH) == date.get(Calendar.MONTH)
					&& e.getEventDateCal().get(Calendar.DATE) == date.get(Calendar.DATE)) {
				ret.add(e);
			}
		}
		return ret;
	}
	
	/**
	 * @param data
	 */
	public void loadDataService(DataService data) {
		this.Data = data;
	}
	
	/**
	 * @return
	 */
	public DataService getDataService() {
		return this.Data;
	}
	
	/**
	 * @param day
	 * @return
	 */
	public String getDayDescription(Calendar day) {
		ArrayList<Event> events = getAllEventsFrom(day);
		
		String desc = "Month: " + day.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "\nDay: " + day.get(Calendar.DATE) + "\n";
        int number = 0;
    	for(Event event : events) {
    		if(event.getOwner().equals(Person.currentPerson)) {
    			desc += "#" + (++number) + "\n" + event.toString() + "\n";
    		}            	
        }
    	
    	return desc;
		
	}
}

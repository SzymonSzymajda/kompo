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
import presentation.Settings;

public class LogicLayer {
	
	private DataService Data;
	
	public LogicLayer() {
		if(Settings.getInstance().database) {
			this.Data = new DataServiceSQL();	
		} else {
			this.Data = new DataService();
		}
	}
	
	/**
	 * @param name name of new Person object
	 * @param surname surname of new Person object
	 */
	public void createPerson(String name, String surname) {
		Data.createPerson(new Person(name, surname));
	}
	
	/**
	 * @param name name of person
	 * @param surname surname of person
	 * @return Person object with given name and surname
	 * @throws LogicLayerException if given Person object with given name and surname does not exist
	 */
	public Person getPerson(String name, String surname) throws LogicLayerException{
		try {
			return Data.getPerson(name, surname);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @param name name of person
	 * @param surname surname of person
	 * @throws LogicLayerException if given Person object with given name and surname does not exist
	 */
	public void deletePerson(String name, String surname) throws LogicLayerException {
		try {
			Data.deletePerson(name, surname);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @param p Person object being deleted
	 * @throws LogicLayerException if given person does not exist
	 */
	public void deletePerson(Person p) throws LogicLayerException {
		try {
			
			Data.deletePerson(p);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Specified person does not exist");
		}
	}
	
	/**
	 * @return list containing all stored Person objects
	 */
	public ArrayList<Person> getAllPeople(){
		return Data.getAllPeople();
	}
	
	/**
	 * @param year year of the event
	 * @param month month of the event
	 * @param day day of the event
	 * @param hour hour of the event
	 * @param minutes minutes of the event
	 * @param description description of the event
	 * @param owner owner of the event
	 */
	public void createEvent(int year, int month, int day, int hour, int minutes, String description, Person owner) {
		Data.createEvent(new Event(year, month, day, hour, minutes, description, owner));
	}
	
	/**
	 * @param year year of the event
	 * @param month month of the event
	 * @param day day of the event
	 * @param hour hour of the event
	 * @param minutes minutes of the event
	 * @param description description of the event
	 * @param notification Notification object linked to the event
	 * @param owner owner of the event
	 */
	public void createEvent(int year, int month, int day, int hour, int minutes, String description, Calendar notification, Person owner) {
		Data.createEvent(new Event(year, month, day, hour, minutes, description, notification, owner));
	}
	
	/**
	 * @param e Event object being added to database
	 */
	public void createEvent(Event e){
		Data.createEvent(e);
	}
	
	/**
	 * @param id key to which sought Event object is mapped
	 * @return Event object mapped to given key
	 * @throws LogicLayerException if given key has no value mapped to it
	 */
	public Event getEvent(int id) throws LogicLayerException {
		try {
			return Data.getEvent(id);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Given ID is not mapped");
		}
	}
	
	/**
	 * @param id key to which updated Event object is mapped
	 * @param e new Event object to be mapped under given key
	 * @throws LogicLayerException if given key has no value mapped to it
	 */
	public void updateEvent(int id, Event e) throws LogicLayerException {
		try {
			Data.updateEvent(id, e);
		} catch (DataServiceException e1) {
			throw new LogicLayerException("Given ID is not mapped");
		}		
	}
	
	/**
	 * @param id key to which Event object being removed is mapped
	 * @throws LogicLayerException if given key has no value mapped to it
	 */
	public void deleteEvent(int id) throws LogicLayerException {
		try {
			Data.deleteEvent(id);
		} catch (DataServiceException e) {
			throw new LogicLayerException("Given ID is not mapped");
		}
	}
	
	/**
	 * @param e Event object being removed
	 * @throws LogicLayerException if given value is not mapped
	 */
	public void deleteEvent(Event e) throws LogicLayerException {
		try {
			Data.deleteEvent(e);
		} catch (DataServiceException e1) {
			throw new LogicLayerException("Given Event is not mapped");
		}
	}
	
	/**
	 * @return map contaning all stored Event objects
	 */
	public TreeMap<Integer, Event> getAllEvents(){
		return Data.getAllEvents();
	}
	
	/**
	 * @param date day whose events are going to be returned
	 * @return list containing events happening on given date
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
	
	public ArrayList<Event> getNotifications(Calendar date){
		Calendar now = (Calendar) date.clone();
		date.add(Calendar.DATE, 7);
		System.out.println(now.getTime());
		System.out.println(date.getTime());
		ArrayList<Event> ret = new ArrayList<Event>();
		for(Event e : this.getAllEvents().values()) {
			if(		   e.getNotification().get(Calendar.YEAR) == now.get(Calendar.YEAR)
					&& e.getNotification().get(Calendar.MONTH) == now.get(Calendar.MONTH)
					&& e.getNotification().get(Calendar.DATE) >= now.get(Calendar.DATE)
					&& e.getNotification().get(Calendar.DATE) <= date.get(Calendar.DATE)){
				System.out.println(e);
				ret.add(e);
			}
		}
		return ret;
	}
	
	/**
	 * @param data DataService object to be set as current DataService
	 */
	public void loadDataService(DataService data) {
		if(Settings.getInstance().database) {
			this.Data = (DataServiceSQL) data;
		}
		else this.Data = data;
	}
	
	/**
	 * @return DataService object
	 */
	public DataService getDataService() {
		return this.Data;
	}
	
	/**
	 * @param day date whose description shall be returned
	 * @return String containing description of all events on given day
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
	
	/**
	 * @param cutoff date before which all events are going to be removed
	 */
	public void removeOldEvents(Calendar cutoff) {
		for(Event e : this.Data.getAllEvents().values()) {
			if(e.getEventDateCal().before(cutoff)) {
				try {
					this.Data.deleteEvent(e);
				} catch (DataServiceException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

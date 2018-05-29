package logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TreeMap;

import data.DataService;
import data.DataServiceException;
import data.Event;
import data.Person;

public class LogicLayer {
	
	private DataService Data = new DataService();
	
	//CRUD Person
		public void createPerson(String name, String surname) {
			Data.createPerson(new Person(name, surname));
		}
		
		public Person getPerson(int id) throws LogicLayerException{
			try {
				return Data.getPerson(id);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public void updatePerson(int id, Person p) throws LogicLayerException {
			try {
				Data.updatePerson(id, p);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}		
		}
		
		public void deletePerson(int id) throws LogicLayerException {
			try {
				Data.deletePerson(id);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public void deletePerson(Person p) throws LogicLayerException {
			try {
				Data.deletePerson(p);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public TreeMap<Integer, Person> getAllPeople(){
			return Data.getAllPeople();
		}
		
	//CRUD Event
		public void createEvent(String description, int year, int month, int day, int hour, int minutes) {
			Data.createEvent(new Event(year, month, day, hour, minutes, description));
		}
		
		public void createEvent(Event e){
			Data.createEvent(e);
		}
		
		public Event getEvent(int id) throws LogicLayerException {
			try {
				return Data.getEvent(id);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public void updateEvent(int id, Event e) throws LogicLayerException {
			try {
				Data.updateEvent(id, e);
			} catch (DataServiceException e1) {
				throw new LogicLayerException("Given ID is not mapped");
			}		
		}
		
		public void deleteEvent(int id) throws LogicLayerException {
			try {
				Data.deleteEvent(id);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public void deleteEvent(Event e) throws LogicLayerException {
			try {
				Data.deleteEvent(e);
			} catch (DataServiceException e1) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
		
		public TreeMap<Integer, Event> getAllEvents(){
			return Data.getAllEvents();
		}
		
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
		
		//do serializacji
		public void loadDataService(DataService data) {
			this.Data = data;
		}
		
		public DataService getDataService() {
			return this.Data;
		}
		
		public String getDayDescription(Calendar day) {
			ArrayList<Event> events = getAllEventsFrom(day);
			
			String desc = "Month: " + day.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "\nDay: " + day.get(Calendar.DATE) + "\n";
            int number = 0;
        	for(Event event : events) {
            	desc += "#" + (++number) + "\n" + event.toString() + "\n";
            }
        	
        	return desc;
			
		}
	

}

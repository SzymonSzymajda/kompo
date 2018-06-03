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
	
	//CRUD Person
		public void createPerson(String name, String surname) {
			Data.createPerson(new Person(name, surname));
		}
		
		public Person getPerson(String name, String surname) throws LogicLayerException{
			try {
				return Data.getPerson(name, surname);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Specified person does not exist");
			}
		}
		
		public void deletePerson(String name, String surname) throws LogicLayerException {
			try {
				Data.deletePerson(name, surname);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Specified person does not exist");
			}
		}
		
		public void deletePerson(Person p) throws LogicLayerException {
			try {
				
				Data.deletePerson(p);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Specified person does not exist");
			}
		}
		
		public ArrayList<Person> getAllPeople(){
			return Data.getAllPeople();
		}
		
	//CRUD Event
		public void createEvent(int year, int month, int day, int hour, int minutes, String description, Person owner) {
			Data.createEvent(new Event(year, month, day, hour, minutes, description, owner));
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
        		if(event.getOwner().equals(Person.currentPerson)) {
        			desc += "#" + (++number) + "\n" + event.toString() + "\n";
        		}            	
            }
        	
        	return desc;
			
		}
	

}

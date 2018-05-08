package logic;

import java.util.Date;
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
		
		public Person getPerson(int id) throws DataServiceException {
			return Data.getPerson(id);
		}
		
		public void updatePerson(int id, Person p) throws DataServiceException {
			Data.updatePerson(id, p);		
		}
		
		public void deletePerson(int id) throws DataServiceException {
			Data.deletePerson(id);
		}
		
		public void deletePerson(Person p) throws DataServiceException {
			Data.deletePerson(p);
		}
		
		public TreeMap<Integer, Person> getAllPeople(){
			return Data.getAllPeople();
		}
		
		//CRUD Event
		public void createEvent(String name, Date date) {
			Data.createEvent(new Event(date, name));
		}
		
		public Event getEvent(int id) throws DataServiceException {
			return Data.getEvent(id);
		}
		public void updateEvent(int id, Event e) throws DataServiceException {
			Data.updateEvent(id, e);		
		}
		public void deleteEvent(int id) throws DataServiceException {
			Data.deleteEvent(id);
		}
		public void deleteEvent(Event e) throws DataServiceException {
			Data.deleteEvent(e);
		}
		public TreeMap<Integer, Event> getAllEvents(){
			return Data.getAllEvents();
		}
		
		public void addPeopleToEvent(int id, Person...persons) throws DataServiceException {
			Data.addPeopleToEvent(id, persons);
		}
	

}

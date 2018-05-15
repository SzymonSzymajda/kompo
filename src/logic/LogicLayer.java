package logic;

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
		public void createEvent(String description, int year, int month, int day) {
			Data.createEvent(new Event(year, month, day, description));
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
		
		public void addPeopleToEvent(int id, Person...persons) throws LogicLayerException {
			try {
				Data.addPeopleToEvent(id, persons);
			} catch (DataServiceException e) {
				throw new LogicLayerException("Given ID is not mapped");
			}
		}
	

}

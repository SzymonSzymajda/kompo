package logic;

import java.util.TreeMap;

import data.DataService;
import data.DataServiceException;
import data.Event;
import data.Person;

public class LogicLayer {
	
	private DataService data = new DataService();
	
	//CRUD Person
		public void createPerson(String name, String surname) {
			data.createPerson(new Person(name, surname));
		}
		public Person getPerson(int id) {
			return data.getPerson(id);
		}
		public void updatePerson(int id, Person p) {
			data.updatePerson(id, p);		
		}
		public void deletePerson(int id) {
			data.deletePerson(id);
		}
		public void deletePerson(Person p) {
			data.deletePerson(p);
		}
		public TreeMap<Integer, Person> getAllPeople(){
			return data.getAllPeople();
		}
		
		//CRUD Event
		public void createEvent(Event e) {
			//to do
		}
		
		public Event getEvent(int id) {
			return data.getEvent(id);
		}
		public void updateEvent(int id, Event e) throws DataServiceException {
			data.updateEvent(id, e);		
		}
		public void deleteEvent(int id) throws DataServiceException {
			data.deleteEvent(id);
		}
		public void deleteEvent(Event e) throws DataServiceException {
			data.deleteEvent(e);
		}
		public TreeMap<Integer, Event> getAllEvents(){
			return data.getAllEvents();
		}
	

}

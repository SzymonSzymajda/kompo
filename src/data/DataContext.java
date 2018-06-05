package data;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Stores all Event and Person objects
 *
 */
public class DataContext {
	ArrayList<Person> People = new ArrayList<Person>();
	TreeMap<Integer, Event> Events = new TreeMap<Integer, Event>();
}

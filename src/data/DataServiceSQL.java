package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

/**
 * Class extending DataService class, containing methods to pull and push data to and from the database
 *
 */
public class DataServiceSQL extends DataService{
	
	private Connection con = null;
	
	/**
	 * Creates a DataServiceSQL object and connects it to the database
	 */
	public DataServiceSQL() {
		super();
		
		try {
			SQLServerDataSource ds = new SQLServerDataSource();
			ds.setIntegratedSecurity(true);
			ds.setServerName("localhost");
			ds.setPortNumber(1433); 
			ds.setDatabaseName("calendar_data");
			con = ds.getConnection();
		} catch (SQLServerException e) {
			e.printStackTrace();
		}
		loadFromDatabase();
	}
	
	/**
	 * Fills DataContext with data from the database
	 */
	private void loadFromDatabase() {
		super.eventCounter = this.setEventCounter();
		super.Data.People = this.loadPeopleFromDatabase();
		super.Data.Events = this.loadEventsFromDatabase();		
	}

	/**
	 * 
	 * @return arrayList containing Person objects pulled from remote database
	 */
	private ArrayList<Person> loadPeopleFromDatabase() {
		ArrayList<Person> ret = new ArrayList<Person>();		
		
		Statement stmt = null;
		String query = "SELECT * FROM calendar_data..People";
		try {
			stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	ret.add(new Person(rs.getString("pName"), rs.getString("pSurname")));
	        }
		} catch(SQLException e) {
			e.printStackTrace();
		}		
		return ret;
	}
	
	/**
	 * @return map containing keys and Event objects pulled from database
	 */
	private TreeMap<Integer, Event> loadEventsFromDatabase() {
		TreeMap<Integer, Event> ret = new TreeMap<Integer, Event>();
		
		Statement stmt = null;
		String query = "SELECT * FROM calendar_data..Events";
		try {
			stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	for(Person p : super.Data.People) {
	        		if(p.getName().equals(rs.getString("ownerName")) && p.getSurname().equals(rs.getString("ownerSurname"))) {
	        			Calendar temp = Calendar.getInstance();
	        			temp.setTime(rs.getTimestamp("notification"));
	        			ret.put(rs.getInt("ID"), new Event(rs.getTimestamp("date"), rs.getString("description"), temp, p));
	        		}
	        	}
	        	
	        }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	/**
	 * @return eventCounter value from database
	 */
	private int setEventCounter() {
		Statement stmt = null;
		String query = "SELECT TOP 1 ID FROM calendar_data..Events ORDER BY ID DESC";
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()) {
				query = "DBCC CHECKIDENT([calendar_data..Events], RESEED, -1)";
				stmt.execute(query);
				return 0;
			}
			return rs.getInt("ID");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#createPerson(data.Person)
	 */
	@Override
	public void createPerson(Person p) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO calendar_data..People VALUES(?, ?)";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getSurname());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		super.createPerson(p);
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#deletePerson(java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePerson(String name, String surname) throws DataServiceException {
		PreparedStatement stmt = null;
		String query = "DELETE FROM calendar_data..People WHERE pName = ? AND pSurname = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		super.deletePerson(name, surname);
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#deletePerson(data.Person)
	 */
	@Override
	public void deletePerson(Person p) throws DataServiceException {
		PreparedStatement stmt = null;
		String query = "DELETE FROM calendar_data..People WHERE pName = ? AND pSurname = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getSurname());
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		super.deletePerson(p);
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#createEvent(data.Event)
	 */
	@Override
	public void createEvent(Event e) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO calendar_data..Events VALUES(?, ?, ?, ?, ?)";
		try {
			stmt = con.prepareStatement(query);
			stmt.setTimestamp(1, new java.sql.Timestamp(e.getEventDateCal().getTime().getTime()));
			stmt.setString(2, e.getDescription());
			stmt.setString(3, e.getOwner().getName());
			stmt.setString(4, e.getOwner().getSurname());
			stmt.setTimestamp(5, new java.sql.Timestamp(e.getNotification().getTime().getTime()));
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}		
		
		super.createEvent(e);
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#updateEvent(int, data.Event)
	 */
	@Override
	public void updateEvent(int id, Event e) throws DataServiceException {
		PreparedStatement stmt = null;
		String query = "UPDATE calendar_data..Events SET date = ?, description = ?, ownerName = ?, ownerSurname = ? WHERE ID = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setTimestamp(1, new java.sql.Timestamp(e.getEventDateCal().getTime().getTime()));
			stmt.setString(2, e.getDescription());
			stmt.setString(3, e.getOwner().getName());
			stmt.setString(4, e.getOwner().getSurname());
			stmt.setTimestamp(5, new java.sql.Timestamp(e.getNotification().getTime().getTime()));
			stmt.setInt(6, id);
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		super.updateEvent(id, e);			
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#deleteEvent(int)
	 */
	@Override
	public void deleteEvent(int id) throws DataServiceException  {
		PreparedStatement stmt = null;
		String query = "DELETE FROM calendar_data..Events WHERE ID = ?";
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		super.deleteEvent(id);
	}
	
	/* (non-Javadoc)
	 * @see data.DataService#deleteEvent(data.Event)
	 */
	@Override
	public void deleteEvent(Event e) throws DataServiceException  {		
		for(Entry<Integer, Event> ev : super.Data.Events.entrySet()) {
			if(ev.getValue() == e) {
				this.deleteEvent(ev.getKey());
			}
		}
	}
}

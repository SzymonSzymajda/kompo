package data;

/**
 * Represents a single person
 *
 */
public class Person {
	private String Name;
	private String Surname;
	public static Person currentPerson;
	
	public Person() {};
	
	/**
	 * Creates a Person object with a given parameters
	 * @param name name of the person
	 * @param surname surname of the person
	 */
	public Person(String name, String surname) {
		super();
		Name = name;
		Surname = surname;
	}	

	/**
	 * Creates a Person object from another Person object
	 * @param currentPerson Person object whose values are going to be copied
	 */
	public Person(Person currentPerson) {
		Name = currentPerson.getName();
		Surname = currentPerson.getSurname();
	}

	/**
	 * Returns the name of the Person
	 * @return String containing name of a person
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the name of the Person
	 * @param name String to be set as a new name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Returns the surname of the Person
	 * @return String containing surname of a person
	 */
	public String getSurname() {
		return Surname;
	}

	/**
	 * Sets the surname of the Person
	 * @param surname String to be set as a new surname
	 */
	public void setSurname(String surname) {
		Surname = surname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Name + " " + Surname;
	}
	
	/**
	 * Compares this Person with a given one
	 * @param p Person object to compare this Person against
	 * @return true if both object fields are equal, false otherwise
	 */
	public boolean equals(Person p) {
		if(this.Name.equals(p.Name) && this.Surname.equals(p.Surname)) {
			return true;
		}
		else {
			return false;
		}
	}
}

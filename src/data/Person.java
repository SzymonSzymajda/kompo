package data;

public class Person {
	private String Name;
	private String Surname;
	public static Person currentPerson;
	
	public Person() {};
	
	public Person(String name, String surname) {
		super();
		Name = name;
		Surname = surname;
	}	

	public Person(Person currentPerson) {
		Name = currentPerson.getName();
		Surname = currentPerson.getSurname();
	}

	/**
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @return
	 */
	public String getSurname() {
		return Surname;
	}

	/**
	 * @param surname
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
	 * @param p
	 * @return
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

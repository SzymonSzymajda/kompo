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

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	@Override
	public String toString() {
		return Name + " " + Surname;
	}
	
	public boolean equals(Person p) {
		if(this.Name.equals(p.Name) && this.Surname.equals(p.Surname)) {
			return true;
		}
		else {
			return false;
		}
	}
}

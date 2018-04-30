package data;

public class Person {
	private String Name;
	private String Surname;
	
	public Person(String name, String surname) {
		super();
		Name = name;
		Surname = surname;
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
	
	
}

import java.util.GregorianCalendar;

public abstract class Persons implements Comparable<Persons> {

	private String firstName;
	private String lastName;
	private char gender='M';
	private GregorianCalendar dateOfBarth;
	
		
	public Persons(String firstName, String lastName, char gender, GregorianCalendar dateOfBarth) {
		this.firstName = firstName;
		this.lastName = lastName;
		setGender( gender);
		this.dateOfBarth = dateOfBarth;
	}
	
	@Override
	public abstract int compareTo(Persons o);
		
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setGender(char gender) {
		if (gender=='F'||gender=='f'||gender=='M'||gender=='m')
		this.gender = gender;
		else System.out.println(" Error: gender must be (M/F)");		
	}
	
	public GregorianCalendar getDateOfBarth() {
		return dateOfBarth;
	}
	
	public void setDateOfBarth(GregorianCalendar dateOfBarth) {
		this.dateOfBarth = dateOfBarth;
	}
	
	
	
}

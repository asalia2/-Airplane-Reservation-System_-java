import java.util.*;

public class Passengers extends Persons  {

	private int passportNumber;
	private String nationality;
	private boolean hasVISA;
	private String seatPreference = "WINDOW";

	private static int numberOfPassenger;

	static Scanner in = new Scanner(System.in);

	public Passengers(String firstName, String lastName, char gender, GregorianCalendar dateOfBarth , int passportNumber,String nationality,
			String seatPreference) {
		super(firstName, lastName, gender, dateOfBarth);
		numberOfPassenger++;
		this.passportNumber = passportNumber;
		this.seatPreference = seatPreference;
		this.nationality=nationality;

	}
	
	public int compareTo(Persons o) { // return 0 if equals & return -1 if not 
		if (o instanceof Passengers) {
			Passengers pass=(Passengers)o;
		if( this.getPassportNumber()==pass.getPassportNumber()) {
			return 0;
		}
		}
		return -1;
	}
	
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isHasVISA() {
		return hasVISA;
	}

	public void setHasVISA(boolean hasVISA) {
		this.hasVISA = hasVISA;
	}

	public String getSeatPreference() {
		return seatPreference;
	}

	public void setSeatPreference(String seatPreference) {
		this.seatPreference = seatPreference;
	}

	public int getPassportNumber() {
		return passportNumber;
	}

	public static int getNumberOfPassenger() {
		return numberOfPassenger;
	}

	public static Passengers getInformation() {

		// String prefer="Aisle";

		System.out.println(" \npassenger's information ...");
		System.out.println("*******************************");
		System.out.println("Pleas enter first name .. "); // first name
		String firstName = in.next().trim();

		System.out.println("Pleas enter last name .. "); // last name
		String lastName = in.next().trim();

		System.out.println("Pleas enter gender (F/M) ..  "); // gender
		char gender = in.next().charAt(0);

//		System.out.println("Pleas enter the date of birth  ..  "); // gender
//		String dob = in.next() ;

		System.out.println("Pleas enter passport number .. "); // passport number
		int pasNump = in.nextInt();

		System.out.println("Pleas enter date of barth (e.g. 22/1/1980 ) ..");
		String[] d = in.next().trim().split("/");
		GregorianCalendar dob1 = new GregorianCalendar(Integer.parseInt(d[2]), Integer.parseInt(d[1]),
				Integer.parseInt(d[0]));
		
		System.out.println("Pleas enter the nationality ..");
		String nationality=in.next().trim();

		System.out.println(" Enter seatpreferance ( Window /  Aisle / None ).. ");

		String seatPreference = in.next().trim().toUpperCase();
//		 if (seatPreference=="WINDOW"||seatPreference=="AISLE"||seatPreference=="NONE")
//			 prefer =seatPreference;

		System.out.println("   ");
		return new Passengers(firstName, lastName, gender, dob1, pasNump,nationality, seatPreference);
	}

	public void printInformation() {
		System.out.println(" Passenger info. \n   =====================");
		System.out.println("Passengers name : " + super.getFirstName() + " " + super.getLastName());
		System.out.println("Gender : " + super.getGender());
		System.out.println("Date Of Barth : " + getDateOfBarth().get(Calendar.YEAR) + "/"
				+ (getDateOfBarth().get(Calendar.MONTH)) + "/" + getDateOfBarth().get(Calendar.DAY_OF_MONTH));
		System.out.println("Passport number : " + passportNumber);
//		System.out.println("passenger nationality : " + nationality);
//		System.out.println("Has an entrance VISA ? " + hasVISA);
	}

}

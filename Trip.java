import java.util.*;

public class Trip implements Comparable<Trip>{

	private Seats[][] seats;
	private String flightNumb;
	private String fromAirportName;
	private String toAirportName;
	private int tripDistance;
//	private GregorianCalendar departureTime;
//	private GregorianCalendar arrivalTime;
	
	private String departureTime;
	private String arrivalTime;

	static Scanner in = new Scanner(System.in);

	public Trip() {
		definArrays();
	}
	
	public Trip(String flightNumb, String fromAirportName, String toAirportName, int tripDistance) {
		super();
		this.flightNumb = flightNumb;
		this.fromAirportName = fromAirportName;
		this.toAirportName = toAirportName;
		this.tripDistance = tripDistance;
	}

	public Trip(String flightNumb, String fromAirportName, String toAirportName, int tripDistance,
			String departureTime, String arrivalTime) {
		definArrays();
		this.flightNumb = flightNumb;
		this.fromAirportName = fromAirportName;
		this.toAirportName = toAirportName;
		this.tripDistance = tripDistance;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;

		// to initialize Seats array

	}

	public static Trip readTripInfo() {

		System.out.println("   Trip Info. \n  ---------------");
		System.out.println(" Pleas enter Number Of Flight  ");
		String flightNumber = in.next().trim().toUpperCase();

		System.out.println(" Pleas enter from airport name  ");
		String fromAirportName = in.next().trim().toUpperCase();

		System.out.println(" Pleas enter to airport name  ");
		String toAirportName = in.next().trim().toUpperCase();

		System.out.println(" Pleas enter trip distance");
		int distance = in.nextInt();

		System.out.println("  Pleas enter trip departure date  (e.g 12/7/2020) ");
		String departure= in.next().trim();
		
		
//		GregorianCalendar departure = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
//				Integer.parseInt(date[0]));

		System.out.println("  Pleas enter trip arrival date  (e.g 12/7/2020) ");
		String  arrival = in.next().trim();
	
//		GregorianCalendar arrival = new GregorianCalendar(Integer.parseInt(date1[2]), Integer.parseInt(date1[1]),
//				Integer.parseInt(date1[0]));

		return new Trip(flightNumber, fromAirportName, toAirportName, distance, departure, arrival);
	}
	
	
	

	@Override
	public int compareTo(Trip o) { // return 0 if equals & return -1 if not 
		if (this.getFlightNumb().equalsIgnoreCase(o.getFlightNumb())) {
			return 0;
		}
		return -1;
	}

	private void definArrays() { // to define array of seat

		seats = new Seats[32][];
		seats[0] = new Seats[0];
		seats[1] = new Seats[4];
		seats[2] = new Seats[4];
		seats[3] = new Seats[4];
		seats[4] = new Seats[0];
		seats[5] = new Seats[0];
		seats[13] = new Seats[0];

		seats[0] = new Seats[0];

		for (int i = 1; i < 32; i++) {

			if (i <= 3) {
				seats[i] = new Seats[4];
				continue;
			}

			if (i == 4 || i == 5 || i == 13) {
				seats[i] = new Seats[0];
				continue;
			}

			seats[i] = new Seats[6];
			
		}
		
		for (int i = 1; i < 4; ++i) {
			for (int j = 0; j < seats[i].length; ++j) {
				this.seats[i][j] = new First_class_seat(i, j);
			}
		}
		for (int i = 6; i <= 31; ++i) {
			for (int j = 0; j < seats[i].length; ++j) {
				this.seats[i][j] = new Economy_class_seat(i, j);
			}
		}

	}

	@Override
	public String toString() {
		return  "\nflightNumb : "
				+ flightNumb + "\nThe trivel from  : " + fromAirportName + " Airport to : " + toAirportName
				+ "  Airport" + "\nThe Trip Distance : " + tripDistance + "\nDeparture Time : "
				+departureTime 
				
//				+ departureTime.get(Calendar.DAY_OF_MONTH) + "/" + departureTime.get(Calendar.MONTH) + "  "
				//+ departureTime.get(Calendar.HOUR_OF_DAY) + ":" + departureTime.get(Calendar.MINUTE) + ":"
				//+ departureTime.get(Calendar.SECOND) 
				+"\nArrival Time : "+ arrivalTime ;
//				+ arrivalTime.get(Calendar.DAY_OF_MONTH)
//				+ "/" + arrivalTime.get(Calendar.MONTH); 
//				 + "  "+ arrivalTime.get(Calendar.HOUR_OF_DAY) + ":"
//				+ arrivalTime.get(Calendar.MINUTE) + ":" + arrivalTime.get(Calendar.SECOND);
	}

	public String getFlightNumb() {
		return flightNumb;
	}

	public void setFlightNumb(String flightNumb) {
		this.flightNumb = flightNumb;
	}

	public Seats getSeat(int row, int col) {
		return seats[row][col];
	}

	public String getFromAirportName() {
		return fromAirportName;
	}

	public void setFromAirportName(String fromAirportName) {
		this.fromAirportName = fromAirportName;
	}

	public String getToAirportName() {
		return toAirportName;
	}

	public void setToAirportName(String toAirportName) {
		this.toAirportName = toAirportName;
	}

	public long getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(int tripDistance) {
		this.tripDistance = tripDistance;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public Seats[][] getSeatsArray() {
		return seats;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


}

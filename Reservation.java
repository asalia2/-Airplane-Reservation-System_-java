import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Reservation {

	private static ArrayList<Trip> triplist;
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("***  Welcom in BZU Flight Trip Reservation System  *** ");
		triplist = new ArrayList<Trip>();

		int k;
//
//		triplist.add(new Trip("JAP123", "Amman", "Tokyo", 10000, "3/5/2020",
//				 "3/8/2020"));
//
//		triplist.add(new Trip("CAN234", "Amman", "Vancouver", 20000,  "3/5/2020",
//				 "3/6/2020"));

		// to read from files
		readTripsFromFiles();
		readPassengersFromFiles();

		while (true) {
			k = menu();
			switch (k) {
			case 0:
				Trip p = Trip.readTripInfo();
				triplist.add(p);
				System.out.println();
				System.out.println(" The flight was successfully added " + p);
				break;
			case 1:
				listTrip(); // sorted by the flight number ******
				break;
			case 2:
				// To reserve a new empty seat suggested be the system (first class or economy)
				// for a new passenger
				// into a specific trip taking into consideration the passenger’s seat
				// preference. 3) To display the passenger information for a specific seat (if
				// the seat is not empty) in a specific
				reserveSeat();
				break;
			case 3:
				// 3) To display the passenger information for a specific seat (if the seat is
				// not empty) in a specific trip.
				// If the seat number is wrong, throw a SeatNumberException and handle it
				// appropriately

				displayInfo();
				break;
			case 4:// list of the first class seat’s passengers
				searchByName();
				break;
			case 5:
				listOfPassenger();// sorted by passport number *****
				break;
			case 6: // change a passenger seat in a specific trip.
				change();
				break;
			case 7:
				cancelReservaion();
				break;
			case 8:
				System.out.println(" Wating for You NEXT TIME ");

				System.out.println("  Thank you ..Go and back Safely  ");
				
				printInFile();
				System.exit(0);
			}
		}
	}
	
	private static void printInFile() throws FileNotFoundException{
		File TripOut = new File("TripOut.txt");
		PrintWriter output=new PrintWriter(TripOut);
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		// Trip info.

		output.println(" Trip information : " + "\n===============================================");

		for (int k = 0; k < triplist.size(); k++) {
			String addToList = triplist.get(k).getFlightNumb();
			list.add(addToList);
		}
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) { // to print list of Trip
			for (int j = 0; j < triplist.size(); j++) {

				if (list.get(i) == triplist.get(j).getFlightNumb()) {
					output.println("\n " + triplist.get(j));
				}

			}
		}
		
		output.close();
		
		// Passenger info.
		
		
		File passengersOut = new File("passengersOut.txt");
		PrintWriter outputPass=new PrintWriter(passengersOut);
		
	
		
		for (int k = 0; k < triplist.size(); k++) {
			
				ArrayList<Integer> listOfPass = new ArrayList<Integer>();

				for (int i = 1; i < 31; i++) { // to create arraylist from passenger's passport
					for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {

						if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

							int addToList = triplist.get(k).getSeatsArray()[i][j].getDetails().getPassportNumber();
							listOfPass.add(addToList);
						}
					}
				}

				Collections.sort(listOfPass);

				outputPass.println(triplist.get(k).getFlightNumb()+" : \n ");

				outputPass.println("  Passenger name      \t  passport number \t number of seat ");

				for (int i1 = 0; i1 < listOfPass.size(); i1++) {

					for (int i = 1; i < 31; i++) { // to print a list of passengers
						for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {
							if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

								if (listOfPass.get(i1) == triplist.get(k).getSeatsArray()[i][j].getDetails()
										.getPassportNumber()) {

									outputPass.println("   "
											+ triplist.get(k).getSeatsArray()[i][j].getDetails().getFirstName()
											+ " " + triplist.get(k).getSeatsArray()[i][j].getDetails().getLastName()
											+ "\t\t    "
											+ triplist.get(k).getSeatsArray()[i][j].getDetails().getPassportNumber()
											+ "\t\t\t" + triplist.get(k).getSeatsArray()[i][j].getSeatNumb()); // ==============
								}
							}
						}
					}
				}
				outputPass.println();
			}
		
		
		outputPass.close();
		
		
	}

	private static void readPassengersFromFiles() throws FileNotFoundException{

		File passengers = new File("C:\\Users\\lg\\Desktop\\Assignments\\Project\\projectjava\\Passengers.txt");

		String[] t;
		if (passengers.exists()) {

			try {

				Scanner in = new Scanner(passengers);
				in.nextLine();

				while (in.hasNext()) {
					t = in.nextLine().trim().split(":");
					if (t.length == 8) {

						String[] name = t[2].trim().split(" ");

						String[] date = t[4].trim().split("/");
						GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(date[2]),
								Integer.parseInt(date[1]), Integer.parseInt(date[0]));

						// public Passengers(String firstName, String lastName, char gender,
						// GregorianCalendar dateOfBarth , int passportNumber,String nationality,
						// String seatPreference)
						Passengers pass = new Passengers(name[0], name[1], t[3].trim().charAt(0), dob,
								Integer.parseInt(t[5].trim()), t[6].trim(), t[7].trim());

						
						
						for (int k = 0; k < triplist.size(); k++) { // Find trip to reserved in
						
							
							if (triplist.get(k).getFlightNumb().equalsIgnoreCase(t[0].trim())) {
								
								for (int i = 1; i < 31; i++) { // Find a seat
									for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {
											
										
										if (triplist.get(k).getSeatsArray()[i][j].getSeatNumb().equalsIgnoreCase(t[1])) {

										
											if (triplist.get(k).getSeatsArray()[i][j].getDetails() == null) { // if seat was empty
												
												//pass.printInformation();																 
												triplist.get(k).getSeatsArray()[i][j].setDetails(pass);

											}
										}
									}
								}
							}
						}
					}
				}

			} catch (FileNotFoundException fileExp) {
				System.out.println(" Error !!");
			}

		} else
			System.out.println(" Error: The File is not Found !! ");
	
	}

	private static void readTripsFromFiles() throws FileNotFoundException {

		File trip = new File("C:\\Users\\lg\\Desktop\\Assignments\\Project\\projectjava\\Trips.txt");

		String[] t;
		if (trip.exists()) {

			try {

				Scanner in = new Scanner(trip);
				in.nextLine();

				while (in.hasNext()) {
					t = in.nextLine().trim().split(":");
					if (t.length == 6) {

						Trip p = new Trip(t[0], t[1], t[2], Integer.parseInt(t[3]), t[4], t[5]);
						triplist.add(p);
					}
				}

			} catch (FileNotFoundException fileExp) {
				System.out.println(" Error !!");
			}

		} else
			System.out.println(" Error: The File is not Found !! ");
//		for (int i = 0; i < triplist.size(); i++) {
//			System.out.println(triplist.get(i));
//		}

	}

	private static void displayInfo() {
		int res = 0;
		int ss = 0;
		if (triplist.size() != 0) { // if their are trip reserved
			System.out.println(" Enter flight number ");
			String flightNumb = in.next().trim().toUpperCase();

			for (int k = 0; k < triplist.size(); k++) {
				String check = triplist.get(k).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumb)) { // if the number entered exsict
					res++;

					Seats seat = (menu3() == 1 ? new First_class_seat() : new Economy_class_seat());
					System.out.println(" \n  Enter the seat you are looking for  ");
					String lookFor = in.next().trim().toUpperCase();
					// seat.setDetails(new Passengers("mmmm", "kkkk", 'M',new
					// GregorianCalendar(11,11,11), 123, "Window"));
					try {
						seat.valid(lookFor);
						for (int i = 1; i < 31; i++) {
							for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {

								if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

									if (lookFor.equalsIgnoreCase(triplist.get(k).getSeatsArray()[i][j].getSeatNumb())) {
										triplist.get(k).getSeatsArray()[i][j].getDetails().printInformation();
										ss++;
										break;
									}
								}
							}
						}

					} catch (SeatNumberException seatNumbEx) {
						System.out.println("Exception occured: " + seatNumbEx);
						System.out.println();
						ss++;
					}
				}
			}
			if (ss == 0)
				System.out.println(" The seat is empty ! ");

			if (res == 0)
				System.out.println("  The number of flight you entered  was not reserved yet  ");
		} else
			System.out.println("  Ther is no flight trip reserved yet ");

	}

	private static void listOfPassenger() {
		int res = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		if (triplist.size() != 0) { // if their are trip reserved
			System.out.println(" Enter flight number   ");
			String flightNumbFrom = in.next().trim().toUpperCase();

			for (int k = 0; k < triplist.size(); k++) {
				String check = triplist.get(k).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumbFrom)) { // if the flight number exsict
					res++;

					for (int i = 1; i < 31; i++) { // to create arraylist from passenger's passport
						for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {

							if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

								int addToList = triplist.get(k).getSeatsArray()[i][j].getDetails().getPassportNumber();
								list.add(addToList);
							}
						}
					}

					Collections.sort(list);

//					for (int i = 0; i < list.size(); i++) { // to print list of passport number
//						System.out.println(list.get(i));
//					}

					System.out.println("  Passenger name      \t  passport number \t number of seat ");

					for (int i1 = 0; i1 < list.size(); i1++) {

						for (int i = 1; i < 31; i++) { // to print a list of passengers
							for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {
								if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

									if (list.get(i1) == triplist.get(k).getSeatsArray()[i][j].getDetails()
											.getPassportNumber()) {

										System.out.println("   "
												+ triplist.get(k).getSeatsArray()[i][j].getDetails().getFirstName()
												+ " " + triplist.get(k).getSeatsArray()[i][j].getDetails().getLastName()
												+ "\t\t    "
												+ triplist.get(k).getSeatsArray()[i][j].getDetails().getPassportNumber()
												+ "\t\t\t" + triplist.get(k).getSeatsArray()[i][j].getSeatNumb()); // ==============
									}
								}
							}
						}
					}
				}
			}
		} else {
			System.out.println("  Ther is no flight trip reserved yet ");
			res++;
		}

		if (res == 0) {
			System.out.println(" The number of flight is not exsict !");
		}

	}

	private static void change() {
		int res = 0;

		if (triplist.size() != 0) { // if their are trip reserved
			System.out.println(" Enter flight number that you want to change  ");
			String flightNumbFrom = in.next().trim().toUpperCase();

			for (int from = 0; from < triplist.size(); from++) {
				String check = triplist.get(from).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumbFrom)) { // if the flight number exsict
					res++;
					// get seat to change

					Seats old = (menu3() == 1 ? new First_class_seat() : new Economy_class_seat()); // to get type of
																									// old seat
																									// 1=>first ||
																									// 2=>economy
					// to create new seat from the same type of old seat
					Seats newS = (old instanceof First_class_seat ? new First_class_seat() : new Economy_class_seat());

					System.out.println("  Enter the old seat ");
					String oldSeat = in.next().trim().toUpperCase();

					if (old.exsict(oldSeat)) { // begin

						for (int i = 1; i < 32; i++) {
							for (int j = 0; j < triplist.get(from).getSeatsArray()[i].length; j++) {
								if (triplist.get(from).getSeatsArray()[i][j].getSeatNumb().equalsIgnoreCase(oldSeat)) {

									if (triplist.get(from).getSeatsArray()[i][j].getDetails() != null) { // if the old
																											// seat is
																											// not empty

										System.out.println(" Enter the new seat you want to reserve in "); // new Seat
										String newSeat = in.next().trim().toUpperCase();
										if (newS.exsict(newSeat)) {

											for (int k = 1; k < 32; k++) {
												for (int k2 = 0; k2 < triplist.get(from)
														.getSeatsArray()[k].length; k2++) {

													if (triplist.get(from).getSeatsArray()[k][k2].getSeatNumb()
															.equalsIgnoreCase(newSeat)) {

														if (triplist.get(from).getSeatsArray()[k][k2]
																.getDetails() == null) {

															Passengers passInNewSeat = new Passengers(
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getFirstName(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getLastName(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getGender(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getDateOfBarth(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getPassportNumber(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getNationality(),
																	triplist.get(from).getSeatsArray()[i][j]
																			.getDetails().getSeatPreference());

															triplist.get(from).getSeatsArray()[k][k2]
																	.setDetails(passInNewSeat);
															triplist.get(from).getSeatsArray()[i][j].setDetails(null);

															System.out.println(
																	" \nCongratulaton !! change seat was successful ");
															break;

														} else
															System.out.println(" The new Seat was reserved !!");
													}
												}
											}

										} else
											System.out.println(" The new seat you enterd is not exsict "); // end => new
																											// Seat

									} else
										System.out.println(" The old seat is empty !");

								}
							}
						}

					} // end if old seat is not exsict
					else
						System.out.println(" Error : The seat is not exsict ");// exsict
				}
			}
		} else {
			System.out.println("  Ther is no flight trip reserved yet ");
			res++;
		}

		if (res == 0) {
			System.out.println(" The number of flight is not exsict !");
		}
	}

	private static int menu3() { // to get type of seat 1=> first || 2=> economy
		System.out.println("  Enter the type of seat    \n (1) for First class  or (2) for Economy class ");

		int r = in.nextInt();
		while ((r != 1) && (r != 2)) {
			System.out.println(" Error: The number you enterd not valid \n ");
			System.out.println(" you has to chose either ( 1 ) for First class or ( 2 ) for Economy ");
			r = in.nextInt();
		}
		return r;
	}

	private static void searchByName() {

		int res = 0;

		if (triplist.size() != 0) { // if their are trip reserved
			System.out.println(" Enter flight number ");
			String flightNumb = in.next().trim().toUpperCase();

			for (int k = 0; k < triplist.size(); k++) {
				String check = triplist.get(k).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumb)) { // if the number entered exsict
					res++;

//					triplist.get(k).getSeats();

					System.out.println("Enter passenger's Name ");
					String name = in.next().trim();

					int person = 0;
					for (int i = 1; i < 32; i++) {
						for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {

							if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {
								if (triplist.get(k).getSeatsArray()[i][j].getDetails().getFirstName()
										.equalsIgnoreCase(name)
										|| triplist.get(k).getSeatsArray()[i][j].getDetails().getLastName()
												.equalsIgnoreCase(name)) {
									System.out.println("Mr./Mrs. " + name + " at seat "
											+ triplist.get(k).getSeatsArray()[i][j].getSeatNumb()); // =============
									person++;

								}
							}
						}
					}
					if (person == 0)
						System.out.println("Mr./Mrs. " + name + " is not found");

				}
			}
			if (res == 0)
				System.out.println("  The number of flight you entered  was not reserved yet  ");
		} else
			System.out.println("  Ther is no flight trip reserved yet ");
	}

	private static void cancelReservaion() {
		int res = 0;
		int person = 0;
		if (triplist.size() != 0) { // if their are trip reserved

			System.out.println(" Enter flight number ");
			String flightNumb = in.next().trim().toUpperCase();
			for (int k = 0; k < triplist.size(); k++) {
				String check = triplist.get(k).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumb)) {
					res++;

					System.out.println(" Pleas enter the number of seat you want to cancel ");
					String cancel = in.next().trim();

					for (int i = 1; i < 32; i++) {
						for (int j = 0; j < triplist.get(k).getSeatsArray()[i].length; j++) {

							if (triplist.get(k).getSeatsArray()[i][j].getSeatNumb(i, j).equalsIgnoreCase(cancel)) {
								if (triplist.get(k).getSeatsArray()[i][j].getDetails() != null) {

									triplist.get(k).getSeatsArray()[i][j].setDetails(null);

									System.out.println(" cancel Reservation was Succesful ");
									person++;
								}
							}
						}
					}
				}
			}
		} else {
			System.out
					.println("Ther is no flight trip reserved yet \n  If you want to reserved flight trip chose 0 \n ");
			res++;
			person++;
		}

		if (res == 0) {
			System.out.println("    The number you entered is not exsict !!");
			person++;
		}
		if (person == 0)
			System.out.println(" The seat  was empty ");
	}

	private static void reserveSeat() {

		int res = 0;

		if (triplist.size() != 0) { // if their are trip reserved

			System.out.println(" Enter flight number ");
			String flightNumb = in.next().trim().toUpperCase();
			for (int i = 0; i < triplist.size(); i++) {
				String check = triplist.get(i).getFlightNumb();
				if (check.equalsIgnoreCase(flightNumb)) {
					res++;

					Seats seat = ((menu2() == 1) ? new First_class_seat() : new Economy_class_seat());
					Passengers passen = Passengers.getInformation();
					Seats s = getSeatSuggested(triplist.get(i), seat, passen); // call method that reserved seat if it
																				// was empty
					if (s != null) {
						System.out.println("  The reservation in  " + s.getSeatNumb() + " Seat  was successful ");
					}
				}
			}

		} else {
			System.out
					.println("Ther is no flight trip reserved yet \n  If you want to reserved flight trip chose 0 \n ");
			res++;
		}

		if (res == 0)
			System.out.println("    The number you entered is not exsict !!");
	}

	private static Seats getSeatSuggested(Trip p, Seats seat, Passengers passen) {

		if (seat instanceof First_class_seat) { // get seat from first class
			for (int i = 1; i <= 3; i++) {
				for (int j = 0; j < p.getSeatsArray()[i].length; j++) {
					if (p.getSeatsArray()[i][j].getDetails() == null) {

						if (passen.getSeatPreference().equalsIgnoreCase("WINDOW") && (j == 0 || j == 3)) {
							p.getSeatsArray()[i][j].setDetails(passen);
							return new First_class_seat(i, j);
						} else if (passen.getSeatPreference().equalsIgnoreCase("Aisle") && (j == 1 || j == 2)) {
							p.getSeatsArray()[i][j].setDetails(passen);
							return new First_class_seat(i, j);
						}
					}
				}
			}
		}

		else { // get seat from economy class
			for (int i = 6; i <= 31; i++) {
				for (int j = 0; j < p.getSeatsArray()[i].length; j++) {
					if (p.getSeatsArray()[i][j].getDetails() == null) {

						if (passen.getSeatPreference().equalsIgnoreCase("WINDOW") && (j == 0 || j == 5)) {
							p.getSeatsArray()[i][j].setDetails(passen);
							return new Economy_class_seat(i, j);
						} else if (passen.getSeatPreference().equalsIgnoreCase("AISLE") && (j == 2 || j == 3)) {
							p.getSeatsArray()[i][j].setDetails(passen);
							return new Economy_class_seat(i, j);
						} else if (passen.getSeatPreference().equalsIgnoreCase("NONE") && (j == 1 || j == 4)) {
							p.getSeatsArray()[i][j].setDetails(passen);
							return new Economy_class_seat(i, j);
						}
					}
				}
			}
		}
		return null;
	}

	private static int menu2() {

		System.out.println(
				"If you want to reserve in First class, Enter the number 1 \n If you want to reserve in Economy class, Enter the number 2\n ");
		int r = in.nextInt();
		while ((r != 1) && (r != 2)) {
			System.out.println("  The number you enterd not valid \n ");
			System.out.println(
					"If you want to reserve in Business class, Enter the number 1 \n If you want to reserve in Economy class, Enter the number 2\n ");
			r = in.nextInt();
		}
		if (r == 1) {
			System.out.println(" Ohh.. You chose  First class ");
		} else if (r == 2) {
			System.out.println(" Ohh..You chose an economy class \n ");
		}
		return r;
	}

	private static void listTrip() {

		ArrayList<String> list = new ArrayList<String>();

		System.out.println(" Trip information : " + "\n===============================================");

		for (int k = 0; k < triplist.size(); k++) {
			String addToList = triplist.get(k).getFlightNumb();
			list.add(addToList);
		}
		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) { // to print list of Trip
			for (int j = 0; j < triplist.size(); j++) {

				if (list.get(i) == triplist.get(j).getFlightNumb()) {
					System.out.println("\n " + triplist.get(j));
				}

			}
		}
	}

	private static int menu() {

		System.out.println("\t\t **************");
		System.out.println(" To add a new trip pleas enter ( 0 ) ");
		System.out.println("To list trips information ( 1 ) ");
		System.out.println("To reserve new seat pleas enter ( 2 ) ");

		System.out.println("To display passenger information pleas enter ( 3 )");

		System.out.println("To search for a passenger by first or last name pleas enter ( 4 )");

		System.out.println("To print a list of  passengers ( 5 ) ");

		System.out.println("To change a passenger seat pleas enter ( 6 )");
		System.out.println("To cancel a passenger reservation  pleas enter ( 7 )");
		System.out.println("To exit execution pleas enter ( 8 )\n");
		int k = in.nextInt();
		while (k < 0 || k > 8) {
			System.out.println("Error, pleas enter another number.. The number must be from 1 to 7\n\n");
			k = in.nextInt();
		}

		return k;
	}

}
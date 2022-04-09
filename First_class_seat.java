import java.util.*;

public class First_class_seat extends Seats {

	private String foodPreference="Caviar";
	private String drinkPreference="Liquorice";
	
	static Scanner in=new Scanner(System.in);
		
	public First_class_seat() {
		this.foodPreference="Caviar";
		this.drinkPreference="Liquorice";
	}

	public First_class_seat(String seatNumb) {
		this(seatNumb,null);
		
	}	

	public First_class_seat(String seatNumb, Passengers details) {
		super(seatNumb, details);
		this.foodPreference="Caviar";
		this.drinkPreference="Liquorice";		
	}
	
	 public First_class_seat( int row,  int col) {
		 this.foodPreference="Caviar";
			this.drinkPreference="Liquorice";
			setSeatNumb(getSeatNumb(row, col));
	    }

	@Override
	public String getSeatNumb(int row, int col) {
		switch (col) {
		case 0:
			return "A"+row;
		case 1:
			return "C"+row;
		case 2:
			return "D"+row;
		case 3:
			return "F"+row;
		}
		return null;
	}

	@Override
	public void  readFromUserSeatNumb() {
		System.out.println(" Enter Seat number ");
		String seat=in.next().trim().toUpperCase();
		
			setSeatNumb(seat);
		

	}

	@Override
	public  boolean exsict(String seat) {
		seat=seat.toUpperCase();
		if (seat.length() == 3 || seat.length() == 2) {
			int row = returnRow(seat);
			char column = returncolumn(seat);
			if (row < 0 || row > 3 || column > 'F' || column < 'A'||column=='B'||column=='E') {
				return false;
			}
			else
					return true;
		}
		return false;
	
	}

	@Override
	public int getColomnInt(String seat) {
		
		if (exsict(seat)) {
			char col=returncolumn(seat);
			switch (col){
			case 'A':
				return 0;
			case 'C':
				return 1;
				
			case 'D':
				return 2;
				
			case 'F':
				return 3;
				
			}	
		}
		return -1;
	}

	public String getFoodPreference() {
		return foodPreference;
	}

	public void setFoodPreference(String foodPreference) {
		this.foodPreference = foodPreference;
	}

	public String getDrinkPreference() {
		return drinkPreference;
	}

	public void setDrinkPreference(String drinkPreference) {
		this.drinkPreference = drinkPreference;
	}

	
}

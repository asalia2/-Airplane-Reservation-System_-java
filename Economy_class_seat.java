import java.util.*;

public class Economy_class_seat extends Seats {
	private String foodPreference = "Fish";
	private String drinkPreference = "Cola";

	static Scanner in = new Scanner(System.in);

	public Economy_class_seat() {
		this.foodPreference = "Fish";
		this.drinkPreference = "Cola";
	}

	public Economy_class_seat(String seatNumb) {
		this(seatNumb, null);
	}

	public Economy_class_seat(String seatNumb, Passengers details) {
		super(seatNumb, details);
		this.foodPreference = "Fish";
		this.drinkPreference = "Cola";
	}

	public Economy_class_seat(int row, int col) {
		this.foodPreference = "Fish";
		this.drinkPreference = "Cola";
		setSeatNumb(getSeatNumb(row, col));
	}

	@Override
	public String getSeatNumb(int row, int col) {

		String c = String.valueOf((char) ('A' + col));
		return c + row;
	}

	@Override
	public void readFromUserSeatNumb() {
		System.out.println(" Enter Seat number ");
		String seat = in.next().trim().toUpperCase();
		setSeatNumb(seat);

	}

	@Override
	public boolean exsict(String seat) {
		seat = seat.toUpperCase();

		if (seat.length() == 3 || seat.length() == 2) {
			int row = returnRow(seat);
			char column = returncolumn(seat);
			if (row < 5 || row > 31 || row == 13 || column > 'F' || column < 'A') {
				return false;
			} else
				return true;
		}
		return false;
	}

	@Override
	public int getColomnInt(String seat) {
		if (exsict(seat)) {
			char colum_char = returncolumn(seat);

			return (int) (colum_char - 'A');
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

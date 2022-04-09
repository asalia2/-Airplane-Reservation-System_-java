
public abstract class Seats implements Comparable<Seats>{

	private int rowNumb;
	private char columnChar;
	private String seatNumb;
	private Passengers details;
	private boolean reseved = false;
	
	public Seats() {

	}
		
	public Seats(String seatNumb) {
		this(seatNumb,null);
	}

	public Seats(String seatNumb, Passengers details) {
		setSeatNumb(seatNumb);
		this.details = details;
	}	

	@Override
	public int compareTo(Seats o) { // return 0 if equals & return -1 if not 
	
		if (this.getSeatNumb().equalsIgnoreCase(o.getSeatNumb())) {
			return 0;
		}
		return -1;
	}
	
	public void valid(String seatNumb)throws SeatNumberException{  
	     if(!exsict(seatNumb))  
	      throw new SeatNumberException(" The Seat is not valid ");  
	     else {
	    	 this.seatNumb = seatNumb;	
	 		this.columnChar=seatNumb.charAt(0);
	 		this.rowNumb=Integer.parseInt(seatNumb.substring(1));
	     }	      
	   }
	
	public int getRowNumb() {
		return rowNumb;
	}
	
	public char getColumnChar() {
		return columnChar;
	}

	public String getSeatNumb() {
		return seatNumb;
	}
	
	public void setSeatNumb(String seatNumb) {
		if (exsict(seatNumb)) {
		this.seatNumb = seatNumb;	
		this.columnChar=seatNumb.charAt(0);
		this.rowNumb=Integer.parseInt(seatNumb.substring(1));
		}		
	}
	
	public Passengers getDetails() {
		return details;
	}
	
	public void setDetails(Passengers details) {
		this.details = details;
	}
	
	public boolean isReseved() {
		return reseved;
	}
	
	public void setReseved(boolean reseved) {
		this.reseved = reseved;
	}
	
	public  abstract  String getSeatNumb(int row,int col);
	public abstract void readFromUserSeatNumb();
	public abstract boolean exsict(String seat);
	public abstract int getColomnInt(String seat);
	
	protected char returncolumn(String seatNumber) { // private
		return seatNumber.charAt(0);
	}
	
	protected int returnRow(String seatNumber) { // private
		return Integer.valueOf(seatNumber.substring(1));
	}
	

}

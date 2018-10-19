package platform1;

import utilities.Accomodations;


public class Reservation 
{
	private String city;
    private String hotelName;
    private int numberOfRooms;
    private int[] occupationCalendar;
    
    public Reservation(String city, String hotelName, int numberOfRooms, int[] occupationCalendar) {
		this.city = city;
		this.hotelName = hotelName;
		this.numberOfRooms = numberOfRooms;
		this.occupationCalendar = occupationCalendar;
	}


	public Reservation() {
		super();
		this.city = "Default";
		this.hotelName = "Default";
		this.numberOfRooms = 5;
		this.occupationCalendar = null;
	}
    
    
    public String exportStateAsColumns() {
        return String.format("| %-8s | %-15s | %-12d |\n",
                this.city,
                this.hotelName,
                this.numberOfRooms
                );
    }
    
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public int[] getOccupationCalendar() {
		return occupationCalendar;
	}


	public void setOccupationCalendar(int[] occupationCalendar) {
		this.occupationCalendar = occupationCalendar;
	}


	@Override
	public String toString() {
		return Accomodations.stringDescription(this);
	}
}

package platform1;

import utilities.Accomodations;

public class Accomodation 
{
	private String city;
    private String hotelName;
    private String numberOfRooms;
    private String occupationCalendar;
    

    public Accomodation(String city, String hotelName, String numberOfRooms, String occupationCalendar) {
		this.city = city;
		this.hotelName = hotelName;
		this.numberOfRooms = numberOfRooms;
		this.occupationCalendar = occupationCalendar;
	}
    
    public Accomodation() {
		super();
		this.city = "Default";
		this.hotelName = "Default";
		this.numberOfRooms = "Default";
		this.occupationCalendar = "Default";
	}
    
    
    public String exportStateAsColumns() {
        return String.format("| %-8s | %-15s | %-12d | %20s |\n",
                this.city,
                this.hotelName,
                this.numberOfRooms,
                this.occupationCalendar);
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

	public String getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(String numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getOccupationCalendar() {
		return occupationCalendar;
	}

	public void setOccupationCalendar(String occupationCalendar) {
		this.occupationCalendar = occupationCalendar;
	}
	
	@Override
	public String toString() {
		return Accomodations.stringDescription(this);
	}
}

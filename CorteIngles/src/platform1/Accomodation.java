package platform1;

import java.util.Random;

public class Accomodation 
{
	private String city;
    private String hotelName;
    private int numberOfRooms;
    private String occupationCalendar;
    
    private static final String[] CITIES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "Leon", "Caceres", "Lugo", "Toledo"};
    private static final String[] HOTELS = {};
    private static final String[] ROOMS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static final String[] CALENDARS = {};

    // Constructor    
    public Accomodation(String city, 
    				String hotelName, 
    				int numberOfRooms,
    				String occupationCalendar) 
    {
		this.city = city;
		this.hotelName = hotelName;
		this.numberOfRooms = numberOfRooms;
		this.occupationCalendar = occupationCalendar;
	}
    
    // Factory Method
    public Accomodation instanceWithRandomAttributes(){
        Random r = new Random();
        
        String city = CITIES[r.nextInt(CITIES.length)];
        int numOfRooms = 1 + r.nextInt(20);
        String hotelName = ROOMS[r.nextInt(ROOMS.length)];;
        String occupationCalendar = CALENDARS[r.nextInt(CALENDARS.length)];;
        
        return new Accomodation(city, hotelName, numOfRooms, occupationCalendar);
    }
    
    
    public String exportStateAsColumns() {
    	StringBuilder sb = new StringBuilder();

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

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	public String getOccupationCalendar() {
		return occupationCalendar;
	}

	public void setOccupationCalendar(String occupationCalendar) {
		this.occupationCalendar = occupationCalendar;
	}
}

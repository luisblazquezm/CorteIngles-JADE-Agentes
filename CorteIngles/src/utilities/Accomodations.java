package utilities;

import java.util.Random;
import platform1.PlatformData;
import platform1.Reservation;

public class Accomodations 
{
	private static Random random;
	
	private static String[] CITIES = {"Vigo",
									  "Plasencia",
									  "Madrid",
									  "Salamanca",
									  "Leon",
									  "Caceres",
									  "Lugo",
									  "Toledo"};
	
    private static String[] HOTELS = {"Trivago",
    								  "Victoria",
    								  "Schönwohnen",
    								  "Alfonso III",
    								  "Hilton",
    								  "Trip Caballo Blanco",
    								  "Four Seasons"};
    
    private static int[] ROOMS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    
    private static int[] CALENDARS = new int[PlatformData.DAYS_OF_MAY];
    
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
    
	public static String stringDescription(Reservation reservation) {
		if (reservation != null) {
			return reservation.getCity() + ": "
		           + reservation.getHotelName() + ": "
				   + reservation.getNumberOfRooms() + ": ";
		} else {
			return null;
		}
	}
	
    public static Reservation instanceWithRandomAttributes(){
    	return new Reservation(
    			Accomodations.getRandomCity(), 
    			Accomodations.getRandomHotel(), 
    			Accomodations.getRandomRoom(),
    			Accomodations.getRandomCalendar()
    			);
    }
    
    public static String getRandomCity() {
		return Accomodations.CITIES[random.nextInt() % Accomodations.CITIES.length];
	}

	public static String getRandomHotel() {
		return Accomodations.HOTELS[random.nextInt() % Accomodations.HOTELS.length];	
	}

	public static int getRandomRoom() {
		return Accomodations.ROOMS[random.nextInt() % Accomodations.ROOMS.length];	
	}
	
	public static int[] getRandomCalendar() {
		for (int i = 0; i < PlatformData.DAYS_OF_MAY; i++)
			Accomodations.CALENDARS[i] = PlatformData.DEFAULT_AVAILABLE_ROOMS;
		
		return CALENDARS;
	}

}

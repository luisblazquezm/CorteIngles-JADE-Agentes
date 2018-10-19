package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import platform1.Reservation;

public class Accomodations 
{
	private static Random random;
	private static String[] CITIES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "Leon", "Caceres", "Lugo", "Toledo"};
    private static String[] HOTELS = {};
    private static int[] ROOMS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private static String[] CALENDAR_DATES = {};
    
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
    
	public static String stringDescription(Reservation reservation) {
		if (reservation != null) {
			return reservation.getCity() + ": "
		           + reservation.getHotelName() + ": "
				   + reservation.getNumberOfRooms() + ": "
				   + reservation.getOccupationCalendar() + ": ";
		} else {
			return null;
		}
	}
	
    public Reservation instanceWithRandomAttributes(){
    	return new Reservation(
    			Accomodations.getRandomCity(), 
    			Accomodations.getRandomHotel(), 
    			Accomodations.getRandomRoom(), 
    			Accomodations.getRandomCalendarDate()
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

	public static List<String> getRandomCalendarDate() {
		List<String> newList = new ArrayList<>();
		
		for(int i = 0; i < 10 ; i++){
			String newDate = Accomodations.CALENDAR_DATES[random.nextInt() % Accomodations.CALENDAR_DATES.length];
			newList.add(newDate);
		}
		return newList;
	}

}

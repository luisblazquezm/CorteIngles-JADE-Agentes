package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import platform1.Accomodation;

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
    
	public static String stringDescription(Accomodation accomodation) {
		if (accomodation != null) {
			return accomodation.getCity() + ": "
		           + accomodation.getHotelName() + ": "
				   + accomodation.getNumberOfRooms() + ": "
				   + accomodation.getOccupationCalendar() + ": ";
		} else {
			return null;
		}
	}
	
    public Accomodation instanceWithRandomAttributes(){
    	return new Accomodation(
    			Accomodations.getCITIES(), 
    			Accomodations.getHOTELS(), 
    			Accomodations.getROOMS(), 
    			Accomodations.getCALENDAR_DATES()
    			);
    }
    
    public static String getCITIES() {
		return Accomodations.CITIES[random.nextInt() % Accomodations.CITIES.length];
	}

	public static String getHOTELS() {
		return Accomodations.HOTELS[random.nextInt() % Accomodations.HOTELS.length];	
	}

	public static int getROOMS() {
		return (1 + random.nextInt(10));	
	}

	public static List<String> getCALENDAR_DATES() {
		List<String> newList = new ArrayList<>();
		
		for(int i = 0; i < 10 ; i++){
			String newDate = Accomodations.CALENDAR_DATES[random.nextInt() % Accomodations.CALENDAR_DATES.length];
			newList.add(newDate);
		}
		return newList;
	}

}

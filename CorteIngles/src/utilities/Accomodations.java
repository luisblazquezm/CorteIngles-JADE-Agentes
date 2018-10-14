package utilities;

import java.util.Random;

import platform1.Accomodation;

public class Accomodations 
{
	private static Random random;
	private static String[] CITIES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "Leon", "Caceres", "Lugo", "Toledo"};
    private static String[] HOTELS = {};
    private static String[] ROOMS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static String[] CALENDARS = {};
    
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
    			Accomodations.getCALENDARS()
    			);
    }
    
    public static String getCITIES() {
		return Accomodations.CITIES[random.nextInt() % Accomodations.CITIES.length];
	}

	public static String getHOTELS() {
		return Accomodations.HOTELS[random.nextInt() % Accomodations.HOTELS.length];	
	}

	public static String getROOMS() {
		return Accomodations.ROOMS[random.nextInt() % Accomodations.ROOMS.length];	
	}

	public static String getCALENDARS() {
		return Accomodations.CALENDARS[random.nextInt() % Accomodations.CALENDARS.length];	
	}



}

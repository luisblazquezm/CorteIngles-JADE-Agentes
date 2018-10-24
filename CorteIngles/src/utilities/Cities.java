package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import platform1.Activity;
import platform1.City;
import platform1.Hotel;

public class Cities 
{
	private static Random random;
	
	private static String[] CITY_NAMES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "Leon",
									  "Caceres", "Lugo", "Toledo", "Málaga", "Sevilla",
									  "Nueva York", "Londres", "París", "Tokyo", "San Petersburgo",
									  "München", "Berlin", "Frankfurt", "Giza", "Marsella",
									  "Roma", "Sofía", "Santorini", "Lübeck", "Moscú"};
        
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
	
    public static City instanceWithRandomAttributes(){
    	
    	int MAX_HOTELS_IN_CITY = 200;
    	int MAX_ACTIVITIES_IN_CITY = 200;
    	
    	// Random number of hotels but less or equal to HOTEL_NAMES.length, so that there
    	// are not many with the same name. Idem for numberOf activities
    	int numberOfHotels = random.nextInt(MAX_HOTELS_IN_CITY) % Hotels.HOTEL_NAMES.length + 1;
    	int numberOfActivities = random.nextInt(MAX_ACTIVITIES_IN_CITY) % Activities.ACTIVITIES.length + 1;
    	
    	return new City(
    			Cities.getRandomCityName(), 
    			Cities.getRandomListOfHotels(numberOfHotels), 
    			Cities.getRandomListOfActivities(numberOfActivities)
    			);
    }
    
    public static List<City> randomList() {
    	List<City> list = new ArrayList<>();
		for (int i = 0; i < random.nextInt() % CITY_NAMES.length + 1; ++i) {
			list.add(Cities.instanceWithRandomAttributes());
		}
		return list;
    }
    
    private static String getRandomCityName() {
		return Cities.CITY_NAMES[random.nextInt() % Cities.CITY_NAMES.length];
	}
    
    private static List<Hotel> getRandomListOfHotels(int length) {
    	
    	List<Hotel> listOfHotels = new ArrayList<>();
    	
    	for (int i = 0; i < length; ++i) {
    		listOfHotels.add(Hotels.instanceWithRandomAttributes());
    	}
    	
		return listOfHotels;
	}
    
    private static List<Activity> getRandomListOfActivities(int length) {
    	
    	List<Activity> listOfActivities = new ArrayList<>();
    	
    	for (int i = 0; i < length; ++i) {
    		listOfActivities.add(Activities.instanceWithRandomAttributes());
    	}
    	
		return listOfActivities;
	}
}

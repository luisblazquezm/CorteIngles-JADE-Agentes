/**
 * @author mrhyd
 * 
 * Wrapper class for City class. It provides with a set of static utilities.
 * 
 */

package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utilities.Utils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class Cities {
	
	/**
	 * Random object used by class
	 */
	private static Random random;
	
	
	/**
	 * Cities' names
	 */
	private static String[] CITY_NAMES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "León",
									  "Cáceres", "Lugo", "Toledo", "Málaga", "Sevilla",
									  "Nueva York", "Londres", "París", "Tokyo", "San Petersburgo",
									  "München", "Berlín", "Frankfurt", "Giza", "Marsella",
									  "Roma", "Sofía", "Santorini", "Lübeck", "Moscú"};
        
	/**
	 * Minimum number of hotels in one city
	 */
	static final int MIN_NUMBER_OF_HOTELS_PER_CITY = 10;
	
	/**
	 * Maximum number of hotels in one city
	 */
	static final int MAX_NUMBER_OF_HOTELS_PER_CITY = 30;
	
	/**
	 * Minimum number of activities in one city
	 */
	static final int MIN_NUMBER_OF_ACTIVITIES_PER_CITY = 10;
	
	/**
	 * Maximum number of activities in one city
	 */
	static final int MAX_NUMBER_OF_ACTIVITIES_PER_CITY = 25;
	
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * @return Random City class's instance
	 */
    public static City instanceWithRandomAttributes(){
    	
    	return new City(
			Cities.getRandomCityName(),
			Hotels.randomList(random.nextInt(
					(MAX_NUMBER_OF_HOTELS_PER_CITY - MIN_NUMBER_OF_HOTELS_PER_CITY + 1)
					+ MIN_NUMBER_OF_HOTELS_PER_CITY)),
			Activities.randomList(random.nextInt(
					(MAX_NUMBER_OF_ACTIVITIES_PER_CITY - MIN_NUMBER_OF_ACTIVITIES_PER_CITY + 1)
					+ MIN_NUMBER_OF_ACTIVITIES_PER_CITY))
			);
    }
    
    /**
     * @param size Number of elements in list
	 * @return Random-length list of random City class's instances
	 */
    public static List<City> randomList(int size) {
    	List<City> list = new ArrayList<>();
    	
		for (int i = 0; i < size; ++i) {
			list.add(Cities.instanceWithRandomAttributes());
		}
	
		return list;
    }
    
    /**
     * @param cityNames List of city names to be printed
     */
    public static void printCityNameList(String[] cityNames) {
    	
    	String title = "CITY";
    	int width = 20;

    	Utils.printStringTable(title, width, cityNames);
    }
    
    /**
     * @param cityNames List of city names to be printed
     */
    public static void printCityNameList(List<String> cityNames) {
    	
    	if (cityNames == null)
    		return;
    	
    	String[] array = new String[cityNames.size()];
    	array = cityNames.toArray(array);
    	
    	Cities.printCityNameList(array);
    }
    
    /**
     * @param cityNames List of city names to be printed
     */
    public static void printCityList(List<City> listOfCities) {
    	
    	if (listOfCities == null)
    		return;
    	
    	String[] array = new String[listOfCities.size()];
    	for (int i = 0; i < array.length; ++i) {
    		array[i] = listOfCities.get(i).getName();
    	}
    	
    	Cities.printCityNameList(array);
    }
    
    /**
     * @param cityNames List of city names to be printed
     */
    public static void printCityList(City[] listOfCities) {
    	
    	if (listOfCities == null)
    		return;
    	
    	String[] array = new String[listOfCities.length];
    	for (int i = 0; i < array.length; ++i) {
    		array[i] = listOfCities[i].getName();
    	}
    	
    	Cities.printCityNameList(array);
    }
    
    /**
     * @return Random name from CITY_NAMES
     */
    private static String getRandomCityName() {
		return Cities.CITY_NAMES[random.nextInt(Cities.CITY_NAMES.length)];
	}
}

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

public class Cities 
{
	
	/**
	 * Random object used by class
	 */
	private static Random random;
	
	
	/**
	 * Cities' names
	 */
	private static String[] CITY_NAMES = {"Vigo", "Plasencia", "Madrid", "Salamanca", "Leon",
									  "Caceres", "Lugo", "Toledo", "Málaga", "Sevilla",
									  "Nueva York", "Londres", "París", "Tokyo", "San Petersburgo",
									  "München", "Berlin", "Frankfurt", "Giza", "Marsella",
									  "Roma", "Sofía", "Santorini", "Lübeck", "Moscú"};
        
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
    			Hotels.randomList(), 
    			Activities.randomList()
    			);
    }
    
    /**
	 * @return Random-length list of random City class's instances
	 */
    public static List<City> randomList() {
    	List<City> list = new ArrayList<>();
    	
		for (int i = 0; i < Cities.CITY_NAMES.length ; ++i) {
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

/**
 * @author mrhyd
 * 
 * Wrapper class for Hotel class. It provides with a set of static utilities. 
 * 
 */

package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import utilities.JadeUtils;
import utilities.PlatformUtils;
import utilities.Utils;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public class Hotels {
	
	/**
	 * Random object used by class
	 */
	private static Random random;
	
	/**
	 * Hotels' names
	 */
	public static String[] HOTEL_NAMES = {"�mister", "Gran V�a", "Inglaterra", "Am�rica", "American",
			  							  "Ant�rtida", "Atl�ntico", "Bah�a", "Biarritz", "Calypso",
			  							  "Caribe", "Casablanca", "Central", "City", "Colonial",
			  							  "Continental", "Costa", "Cumel�n", "Del Valle", "El cruce",
			  							  "Grand", "Imperial", "Internacional", "Libertador", "Maison",
			  							  "Majestic", "Mansion", "Metropol", "M�naco", "Moderno",
			  							  "Montecarlo", "Nacional", "Palace", "Par�s", "Place",
			  							  "Plaza", "Presidente", "Princesa", "Real", "Rep�blica",
			  							  "Rey", "Riviera", "Royale", "San Mart�n", "Saint Tropez",
			  							  "Select", "Sol", "Superior", "Tropical", "Torino"
	};
	
	/**
	 * Minimum number of free rooms at boot
	 */
	static final int MIN_NUMBER_OF_FREE_ROOMS = 10;
	
	/**
	 * Maximum number of free rooms at boot
	 */
	static final int MAX_NUMBER_OF_FREE_ROOMS = 100;
	
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * @return Random Hotel class's instance
	 */
	public static Hotel instanceWithRandomAttributes() {
		return new Hotel(Hotels.getRandomHotelName(), Hotels.getRandomOccupationCalendar());
	}
	
	/**
	 * @param size Number of elements in list
	 * @return Random-length list of random Hotel class's instances
	 */
	public static List<Hotel> randomList(int size) {
		
    	List<Hotel> list = new ArrayList<>();
    	
		for (int i = 0; i < size; ++i) {
			list.add(Hotels.instanceWithRandomAttributes());
		}
		
		return list;
    }
	
	/**
	 * @return Random name from HOTEL_NAMES
	 */
	private static String getRandomHotelName() {
		return Hotels.HOTEL_NAMES[random.nextInt(Hotels.HOTEL_NAMES.length)];	
	}
	
	/**
	 * @return Random rooms' representation as stated in the occupationCalendar field of Hotel class
	 */
	private static int[] getRandomOccupationCalendar() {
		
		int[] occupationCalendar = new int[PlatformUtils.DAYS_OF_MAY];
		
		for (int i = 0; i < PlatformUtils.DAYS_OF_MAY; ++i) {
			occupationCalendar[i] = random.nextInt(
					(MAX_NUMBER_OF_FREE_ROOMS - MIN_NUMBER_OF_FREE_ROOMS + 1))
					+ MIN_NUMBER_OF_FREE_ROOMS;
		}
		
		return occupationCalendar;
	}


    /**
     * @param hotelNames List of hotel names to be printed
     */
    public static void printHotelNameList(String[] hotelNames) {
    	
    	String title = "HOTEL";
    	int width = 20;

    	Utils.printStringTable(title, width, hotelNames);
    }
    
    /**
     * @param hotelNames List of hotel names to be printed
     */
    public static void printHotelNameList(List<String> hotelNames) {
    	
    	if (hotelNames == null)
    		return;
    	
    	String[] array = new String[hotelNames.size()];
    	array = hotelNames.toArray(array);
    	
    	Hotels.printHotelNameList(array);
    }
    
    /**
     * @param hotelNames List of hotel names to be printed
     */
    public static void printHotelList(List<Hotel> listOfHotels) {
    	
    	if (listOfHotels == null)
    		return;
    	
    	String[] array = new String[listOfHotels.size()];
    	for (int i = 0; i < array.length; ++i) {
    		array[i] = listOfHotels.get(i).getName();
    	}
    	
    	Hotels.printHotelNameList(array);
    }
    
    /**
     * @param cityNames List of hotel names to be printed
     */
    public static void printHotelList(City[] listOfHotels) {
    	
    	if (listOfHotels == null)
    		return;
    	
    	String[] array = new String[listOfHotels.length];
    	for (int i = 0; i < array.length; ++i) {
    		array[i] = listOfHotels[i].getName();
    	}
    	
    	Hotels.printHotelNameList(array);
    }
}

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
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
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
	public static String[] HOTEL_NAMES = {"Ámister", "Gran Vía", "Inglaterra", "América", "American",
			  							  "Antártida", "Atlántico", "Bahía", "Biarritz", "Calypso",
			  							  "Caribe", "Casablanca", "Central", "City", "Colonial",
			  							  "Continental", "Costa", "Cumelén", "Del Valle", "El cruce",
			  							  "Grand", "Imperial", "Internacional", "Libertador", "Maison",
			  							  "Majestic", "Mansion", "Metropol", "Mónaco", "Moderno",
			  							  "Montecarlo", "Nacional", "Palace", "París", "Place",
			  							  "Plaza", "Presidente", "Princesa", "Real", "República",
			  							  "Rey", "Riviera", "Royale", "San Martín", "Saint Tropez",
			  							  "Select", "Sol", "Superior", "Tropical", "Torino"
	};
	
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
	 * @return Random-length list of random Hotel class's instances
	 */
	public static List<Hotel> randomList() {
		
    	List<Hotel> list = new ArrayList<>();
    	
		for (int i = 0; i < Hotels.HOTEL_NAMES.length ; ++i) {
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
		
		int numberOfRooms = random.nextInt(PlatformUtils.MAX_ROOMS_IN_HOTEL) + 1;
		int[] occupationCalendar = new int[PlatformUtils.DAYS_OF_MAY];
		
		for (int i = 0; i < PlatformUtils.DAYS_OF_MAY; ++i) {
			occupationCalendar[i] = numberOfRooms;
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

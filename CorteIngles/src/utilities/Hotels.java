/**
 * @author mrhyd
 * 
 * Wrapper class for Hotel class. It provides with a set of static utilities. 
 * 
 */

package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import platform1.Hotel;

/**
 * @author mrhyd
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
		for (int i = 0; i < random.nextInt() % HOTEL_NAMES.length + 1; ++i) {
			list.add(Hotels.instanceWithRandomAttributes());
		}
		return list;
    }
	
	/**
	 * @return Random name from HOTEL_NAMES
	 */
	private static String getRandomHotelName() {
		return Hotels.HOTEL_NAMES[random.nextInt() % Hotels.HOTEL_NAMES.length];	
	}
	
	/**
	 * @return Random rooms' representation as stated in the occupationCalendar field of Hotel class
	 */
	private static int[] getRandomOccupationCalendar() {
		
		int MAX_ROOMS_IN_HOTEL = 100;
		int MAX_PEOPLE_IN_ROOM = 4;
		
		int numberOfRooms = random.nextInt(MAX_ROOMS_IN_HOTEL) + 1;
		int[] occupationCalendar = new int[numberOfRooms];
		
		for (int i = 0; i < numberOfRooms; ++i) {
			occupationCalendar[i] = random.nextInt(MAX_PEOPLE_IN_ROOM) + 1;
		}
		
		return occupationCalendar;
	}
}

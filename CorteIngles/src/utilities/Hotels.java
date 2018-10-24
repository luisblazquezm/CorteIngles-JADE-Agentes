/**
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
	
	private static Random random;
	
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
	
	public static Hotel instanceWithRandomAttributes() {
		return new Hotel(Hotels.getRandomHotelName(), Hotels.getRandomOccupationCalendar());
	}
	
	public static List<Hotel> randomList() {
    	List<Hotel> list = new ArrayList<>();
		for (int i = 0; i < random.nextInt() % HOTEL_NAMES.length + 1; ++i) {
			list.add(Hotels.instanceWithRandomAttributes());
		}
		return list;
    }
	
	public static String getRandomHotelName() {
		return Hotels.HOTEL_NAMES[random.nextInt() % Hotels.HOTEL_NAMES.length];	
	}
	
	public static int[] getRandomOccupationCalendar() {
		
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

/**
 * 
 */
package data;

import java.util.Comparator;

/**
 * @author mrhyd
 *
 */
public class HotelComparator implements Comparator<Hotel> {

	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {
		if (hotel1 == null || hotel2 == null)
			return 0;
		else
			return hotel1.getName().compareToIgnoreCase(hotel2.getName());
	}

}

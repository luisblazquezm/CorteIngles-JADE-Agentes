package data;

import java.util.Comparator;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class HotelComparator implements Comparator<Hotel> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Hotel hotel1, Hotel hotel2) {
		if (hotel1 == null || hotel2 == null)
			return 0;
		else
			return hotel1.getName().compareToIgnoreCase(hotel2.getName());
	}

}

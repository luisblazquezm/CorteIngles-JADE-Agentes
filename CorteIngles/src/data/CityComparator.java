package data;

import java.util.Comparator;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public class CityComparator implements Comparator<City> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(City city1, City city2) {
		if (city1 == null || city2 == null)
			return 0;
		else
			return city1.getName().compareToIgnoreCase(city2.getName());
	}

}

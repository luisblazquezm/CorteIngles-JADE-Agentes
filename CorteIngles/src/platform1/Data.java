/**
 * @author mrhyd
 * 
 * This is a fully static class, that emulates a database from which agents get information.
 * It stores the list of cities, which in turn contain lists of hotels and activities. 
 */

package platform1;

import java.util.ArrayList;
import java.util.List;

import utilities.Cities;

/**
 * @author mrhyd
 *
 */
public class Data {

	/**
	 * List of cities in 'database'
	 */
	private static List<City> listOfCities;
	
	
	static {
		listOfCities = Cities.randomList();
	}
	
	
	/**
	 * @param citiName Name of the city in which we want to look for activities
	 * @param day Day in which we want to carry out the activity
	 * @return List of activities which comply with the requirements (city and dates)
	 */
	public static List<Activity> getActivities(String cityName, int day) {
		
		List<Activity> list = new ArrayList<>();
		
		City city = null;
		for (City c : listOfCities) {
			if (c.getName().equals(cityName))
				city = c;
		}
		
		if (city == null) {
			return null;
		} else {
			
			for (Activity a : city.getListOfActivities()) {
				if (a.getScheduleDescription()[0] <= day && a.getScheduleDescription()[1] >= day) {
					list.add(a);
				}
			}
			
			return list;
		}
	}
	
	// NullPointerException !!
	public static boolean checkAvailability(String cityName, String hotelName, int departureDay, int returnDay){
			
		City city = null;
		for (City c : listOfCities) {
			if (c.getName().equals(cityName))
				city = c;
		}
		
		Hotel hotel = null;
		if (city == null) {
			return false;
		} else {
			
			for (Hotel h : city.getListOfHotels()) {
				if (h.getName().equals(hotelName)) {
					hotel = h;
				}
			}
			
		}
		
		if (hotel == null) {
			return false;
		} else {
			try {
				return hotel.addCustomer(departureDay, returnDay, 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	   
	}

	public static List<City> getListOfCities() {
		listOfCities = Cities.randomList();
		return listOfCities;
	}
	
	

}

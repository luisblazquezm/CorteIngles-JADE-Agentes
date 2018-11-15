/**
 * @author mrhyd
 * 
 * This is a fully static class, that emulates a database from which agents get information.
 * It stores the list of cities, which in turn contain lists of hotels and activities. 
 */

package data;

import java.util.ArrayList;
import java.util.List;

import utilities.Debug;
import utilities.PlatformUtils;

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


	/**
	 * @param reservationData
	 * @return
	 */
	public static boolean reservationRequestIsAvailable(String[] reservationData){
		
		String cityName = reservationData[PlatformUtils.SENDER_CITY_INDEX];
		String hotelName = reservationData[PlatformUtils.SENDER_HOTEL_INDEX];
		
		String departureDate = reservationData[PlatformUtils.SENDER_DEPARTURE_INDEX];
		String[] partsOfDate = departureDate.split("/");
		int departureDay = Integer.parseInt(partsOfDate[0]); // Gets only the day dd from dd/MM/yyyy
		
		String returnDate = reservationData[PlatformUtils.SENDER_RETURN_INDEX];
		partsOfDate = returnDate.split("/");
		int returnDay = Integer.parseInt(partsOfDate[0]);
			
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
				Debug.message("Data: reservationRequestIsAvailable");
				e.printStackTrace();
			}
		}
		
		return false;
	   
	}

	public static List<City> getListOfCities() {
		return listOfCities;
	}
	
	public static List<String> getListOfCityNames() {
		List<String> list = new ArrayList<>();
		for (City c : listOfCities) {
			list.add(c.getName());
		}
		return list;
	}
	
	public static String[] getArrayOfCityNames() {
		String[] array = new String[listOfCities.size()];
		array = getListOfCityNames().toArray(array);
		return array;
	}
	
	public static List<Hotel> getListOfHotels(String cityName) {

		City city = null;
		
		if (cityName == null)
			return null;
		
		for (City c : listOfCities) {
			if (c.getName().equals(cityName)) {
				city = c;
				break;
			}
		}
		
		if (city == null)
			return null;
		
		return city.getListOfHotels();
	}
	
	public static List<String> getListOfHotelNames(String cityName) {
		List<String> list = new ArrayList<>();
		for (Hotel c : Data.getListOfHotels(cityName)) {
			list.add(c.getName());
		}
		return list;
	}
	
	public static String[] getArrayOfHotelNames(String cityName) {
		
		List<String> list = Data.getListOfHotelNames(cityName);
		String[] array = new String[list.size()];
		array = list.toArray(array);
		return array;
	}

}

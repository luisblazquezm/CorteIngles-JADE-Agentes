package utilities;

import java.util.Date;
import java.util.Random;

import platform1.Activity;

public class Activities {
		
	private static Random random;
	public static String[] CITIES = {};
	public static String[] ACTIVITIES = {};
	public static Date[] SCHEDULE_DESCRIPTIONS = {};
	
	static {
		random = new Random();
		random.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * Returns a string description of the 'activity' parameter
	 * @param activity Activity to be described
	 * @return String description of activity
	 */
	public static String stringDescription(Activity activity) {
		if (activity != null) {
			return activity.getCity() + ": "
		           + activity.getActivity() + ": "
				   + activity.getScheduleDescription().toString();
		} else {
			return null;
		}
	}
	/**
	 * Returns a randomly generated instance of an activity
	 * @return Randomly generated Activity object
	 */
	public static Activity instanceWithRandomAttributes() {
		return new Activity(
			Activities.getRandomCity(),
			Activities.getRandomActivity(),
			Activities.getRandomScheduleDescription()
		);
		
	}
	/**
	 * Returns a random city from the static field CITIES
	 * @return Random city
	 */
	public static String getRandomCity() {
		return Activities.CITIES[random.nextInt() % Activities.CITIES.length];
	}
	/**
	 * Returns a random activity from the static field ACTIVITIES
	 * @return Random activity
	 */
	public static String getRandomActivity() {
		return Activities.ACTIVITIES[random.nextInt() % Activities.ACTIVITIES.length];
	}
	/**
	 * Returns a random schedule description from the static field SCHEDULE_DESCRIPTIONS
	 * @return Random schedule description
	 */
	public static Date getRandomScheduleDescription() {
		return Activities.SCHEDULE_DESCRIPTIONS[random.nextInt() % Activities.SCHEDULE_DESCRIPTIONS.length];
	}

}

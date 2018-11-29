/**
 * @author mrhyd
 * 
 * Wrapper class for Activity class. It provides with a set of static utilities.
 */

package data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class Activities {
	
	/**
	 * Random object used by class
	 */
	private static Random random;
	
	/**
	 * Available activities
	 */
	public static String[] ACTIVITIES = {"Paseo en barco", "Montañismo", "Salto en paracaídas",
									     "Paintball", "Quads en campo abierto", "Puenting",
									     "Tiro con arco", "Escalada", "Baile de salón", "Espectáculo de magia",
									     "Visita a ruinas antiguas", "Spa", "Masaje", "Visita al acuario",
									     "Visita al zoológico", "Espectáculo con aves rapaces", "Cabaret",
									     "Maratón", "Proyección cinematográfica", "Cena buffet",
									     "Degustación de vino", "Clases de tango", "Clases de alfarería",
									     "Surf", "Fiesta ibicenca", "Visita al castillo", "Casa del terror"};
	/**
	 * Date format used throughout
	 */
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
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
			return activity.getName() + ": "
				   + activity.getScheduleDescription()[0] + ": "
				   + activity.getScheduleDescription()[1] + ": ";
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
			Activities.getRandomActivity(),
			Activities.getRandomScheduleDescription()
		);
	}
	
	/**
	 * @param size Number of elements in list
	 * @return List of random activities
	 */
	public static List<Activity> randomList(int size) {
		
		List<Activity> list = new ArrayList<>();
		
		for (int i = 0; i < size; ++i) {
			list.add(Activities.instanceWithRandomAttributes());
		}
		
		return list;
	}
	
	/**
	 * @param size Number of elements in list
	 * @return List of random activities
	 */
	public static List<Activity> randomListAtLeastOnePerDay(int size) {
		
		Activity activity = null;
		List<Activity> list = new ArrayList<>();
		
		for (int i = 0; i < size; ++i) {
			
			Date[] scheduleDescription = null;
			
			try {
				
				if (i < 10) {
					
					scheduleDescription = new Date[] {
							dateFormat.parse("0" + i + "/05/2019"),
							dateFormat.parse((random.nextInt(31 - i) + i + 1) + "/05/2019")
					};
					
					activity = new Activity(Activities.getRandomActivity(), scheduleDescription);
					
				} else if (i <= 30){
					
					scheduleDescription = new Date[] {
							dateFormat.parse(i + "/05/2019"),
							dateFormat.parse((random.nextInt(31 - i) + i + 1) + "/05/2019")
					};
					
					activity = new Activity(Activities.getRandomActivity(), scheduleDescription);
					
				} else {
					activity = Activities.instanceWithRandomAttributes();
				}
				
				
				
			} catch (ParseException e) {
				System.err.println("Activities: randomListAtLeastOnePerDay: ParseException");
				e.printStackTrace();
			}
			
			list.add(activity);
		}
		
		return list;
	}
	
	/**
	 * Returns a random activity from the static field ACTIVITIES
	 * @return Random activity
	 */
	private static String getRandomActivity() {
		return Activities.ACTIVITIES[random.nextInt(Activities.ACTIVITIES.length)];
	}
	/**
	 * Returns a random schedule description from the static field SCHEDULE_DESCRIPTIONS
	 * @return Random schedule description
	 */
	private static Date[] getRandomScheduleDescription() {
		int start, end;
		Date[] scheduleDescription = null;
		start = random.nextInt(30) + 1; 			// 1 - 31
		end = start + random.nextInt(31 - start);	// start - 31
		try {
			scheduleDescription = new Date[] {
					dateFormat.parse(start + "/05/2019"),
					dateFormat.parse(end + "/05/2019")};
		} catch (ParseException e) {
			System.err.println("Activities: getRandomScheduleDescription: ParseException");
			e.printStackTrace();
		}
		
		return scheduleDescription;
	}
}

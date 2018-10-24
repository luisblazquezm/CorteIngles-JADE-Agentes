package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import platform1.Activity;

public class Activities {
		
	private static Random random;
	
	public static String[] ACTIVITIES = {"Paseo en barco", "Montañismo", "Salto en paracaídas",
									     "Paintball", "Quads en campo abierto", "Puenting",
									     "Tiro con arco", "Escalada", "Baile de salón", "Espectáculo de magia",
									     "Visita a ruinas antiguas", "Spa", "Masaje", "Visita al acuario",
									     "Visita al zoológico", "Espectáculo con aves rapaces", "Cabaret",
									     "Maratón", "Proyección cinematográfica", "Cena buffet",
									     "Degustación de vino", "Clases de tango", "Clases de alfarería",
									     "Surf", "Fiesta ibicenca", "Visita al castillo", "Casa del terror"};
	
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
			return activity.getActivity() + ": "
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
	 * @return List of random activities
	 */
	public static List<Activity> randomList() {
		List<Activity> list = new ArrayList<>();
		for (int i = 0; i < random.nextInt() % ACTIVITIES.length + 1; ++i) {
			list.add(Activities.instanceWithRandomAttributes());
		}
		return list;
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
	public static int[] getRandomScheduleDescription() {
		int start, end;
		start = random.nextInt(30) + 1; 			// 1 - 31
		end = start + random.nextInt(31 - start);	// start - 31
		return new int[] {start, end};
	}

}

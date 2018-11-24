/**
 * 
 */
package data;

import java.util.Comparator;

/**
 * @author mrhyd
 *
 */
public class ActivityComparator implements Comparator<Activity> {

	@Override
	public int compare(Activity activity1, Activity activity2) {
		if (activity1 == null || activity2 == null)
			return 0;
		else
			return activity1.getName().compareToIgnoreCase(activity2.getName());
	}
}

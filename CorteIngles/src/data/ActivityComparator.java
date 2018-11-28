package data;

import java.util.Comparator;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
 *
 */
public class ActivityComparator implements Comparator<Activity> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Activity activity1, Activity activity2) {
		if (activity1 == null || activity2 == null)
			return 0;
		else
			return activity1.getName().compareToIgnoreCase(activity2.getName());
	}
}

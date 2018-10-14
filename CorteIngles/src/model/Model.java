package model;

import platform1.Accomodation;
import platform1.Activity;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Accomodation> listAccomodations = new ArrayList<>();
    private List<Activity> listActivities = new ArrayList<>();
    
	public List<Accomodation> getListAccomodations() {
		return listAccomodations;
	}
	public List<Activity> getListActivities() {
		return listActivities;
	}
	public void setListAccomodations(Accomodation accomodation) {
		this.listAccomodations.add(accomodation);
	}
	public void setListActivities(Activity activity) {
		this.listActivities.add(activity);
	}
    
  
}

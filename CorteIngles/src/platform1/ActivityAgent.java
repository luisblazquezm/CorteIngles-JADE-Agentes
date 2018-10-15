package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class ActivityAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		
		JadeUtils.registerService(this, "activity-service", "add-activity");
		JadeUtils.registerService(this, "activity-service", "retrieve-activity");
		
	}

	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
	
	
}

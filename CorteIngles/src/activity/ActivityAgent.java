package activity;

import jade.core.Agent;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
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
		
		String[][] services = {{PlatformUtils.RETRIEVE_ACTIVITY_SER,
				  			   getLocalName()}};
		
		JadeUtils.registerServices(this, services);
		
		ActivityAgentCyclicBehaviour activityBehaviour = new ActivityAgentCyclicBehaviour(this);
		this.addBehaviour(activityBehaviour);
		
	}

	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
	
	
}

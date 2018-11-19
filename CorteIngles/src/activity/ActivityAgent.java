/**
 * @author mrhyd
 * 
 * This class is the agent named 'Ocio' in the task's PDF file.
 * 
 */

package activity;

import jade.core.Agent;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

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
		
		/*
		 *  At least initially, this is not needed, but I just wrote it \_('-')_/
		 */
//		JadeUtils.registerService(this, "activity-service", "add-activity");
		Debug.message("Activity going to register\n");
		
		String[][] services = {{PlatformUtils.RETRIEVE_ACTIVITY_SER,
				  			   getLocalName()}};
		
		JadeUtils.registerServices(this, services);
		
	}

	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
	
	
}

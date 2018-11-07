/**
 * @author mrhyd
 * 
 * This class is the agent named 'CorteIngles' in the task's PDF file.
 * 
 */

package platform1;

import jade.core.Agent;
import utilities.Debug;
import utilities.JadeUtils;
import jade.core.behaviours.ParallelBehaviour;

/**
 * @author mrhyd
 *
 */
public class CorteInglesAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	protected void setup() {
		
		String[][] services = {
				{PlatformData.HANDLE_RESERVATION_SER, getLocalName()},
				{PlatformData.HANDLE_ACTIVITY_SER, getLocalName()}
		};
		
		JadeUtils.registerServices(this, services);
		
		if (Debug.IS_ON)
			System.out.println("CorteInglesAgent services registered");
		
		ParallelBehaviour corteInglesBehaviour = 
				new ParallelBehaviour(this, ParallelBehaviour.WHEN_ANY);
		
		corteInglesBehaviour.addSubBehaviour(
			new ServeUserBehaviour()
		);
		corteInglesBehaviour.addSubBehaviour(
			new GetInformResponseBehaviour()
		);
		
		this.addBehaviour(corteInglesBehaviour);
		
	}

	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
	
	

}

/**
 * 
 */
package platform1;

import jade.core.Agent;
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
		
		JadeUtils.registerService(this,
				                  PlatformData.HANDLE_RESERVATION_SER,
				                  getLocalName());
		JadeUtils.registerService(this,
				                  PlatformData.HANDLE_ACTIVITY_SER,
				                  getLocalName());
		
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

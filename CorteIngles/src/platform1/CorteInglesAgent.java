/**
 * 
 */
package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

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
		
	}

	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
	
	

}

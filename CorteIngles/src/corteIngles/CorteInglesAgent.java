/**
 * @author mrhyd
 * 
 * This class is the agent named 'CorteIngles' in the task's PDF file.
 * 
 */

package corteIngles;

import jade.core.Agent;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import jade.core.behaviours.ParallelBehaviour;

/**
 * @author Luis Bl�zquez Mi�ambres y Samuel G�mez S�nchez
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
				{PlatformUtils.HANDLE_RESERVATION_SER, getLocalName()},
				{PlatformUtils.HANDLE_ACTIVITY_SER, getLocalName()}
		};
		
		JadeUtils.registerServices(this, services);
		
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

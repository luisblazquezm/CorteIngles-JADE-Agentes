package user;

import jade.core.Agent;
import utilities.JadeUtils;
import utilities.PlatformUtils;
import utilities.Utils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class UserAgent extends Agent  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	public void setup()
	{
		// Initialize Utils' resources
		Utils.init();
		
		String[][] services = {
				{PlatformUtils.HANDLE_USER_REQUEST_SER, getLocalName()}
		};
		
		JadeUtils.registerServices(this, services);
		
		UserAgentCyclicBehaviour userBehaviour = new UserAgentCyclicBehaviour(this);
		this.addBehaviour(userBehaviour);
		
	}
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
		//Utils.cleanResources();
	}
}

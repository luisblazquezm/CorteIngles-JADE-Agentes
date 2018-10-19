package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class UserAgent extends Agent  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected UserAgentCyclicBehaviour cB;
	
	@Override
	public void setup()
	{
		//Define servies
		JadeUtils.registerService(this,
								  PlatformData.USER_ALIAS,
								  PlatformData.HANDLE_USER_REQUEST_SER);
		
		//Define behaviour
		cB = new UserAgentCyclicBehaviour(this);
		addBehaviour(cB);
		
		// rEcogido en el texot
		
	}
}

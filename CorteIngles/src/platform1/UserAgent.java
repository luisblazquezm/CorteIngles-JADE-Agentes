package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class UserAgent extends Agent  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setup()
	{
		JadeUtils.registerService(this,
								  PlatformData.USER_ALIAS,
								  PlatformData.HANDLE_USER_REQUEST_SER);
		
		UserAgentCyclicBehaviour userBehaviour = new UserAgentCyclicBehaviour(this);
		this.addBehaviour(userBehaviour);
		
	}
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
}

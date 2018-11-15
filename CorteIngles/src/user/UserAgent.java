package user;

import jade.core.Agent;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

public class UserAgent extends Agent  
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setup()
	{
		String[][] services = {
				{PlatformUtils.HANDLE_USER_REQUEST_SER, getLocalName()}
		};
		
		if (Debug.IS_ON)
			System.out.println("Reservation going to register\n");
		
		JadeUtils.registerServices(this, services);
		
		if (Debug.IS_ON)
			System.out.println("Reservation going to register\n");
		
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

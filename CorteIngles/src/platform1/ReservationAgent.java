package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class ReservationAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ReservationAgentCyclicBehaviour cB;
	
	@Override
	public void setup()
	{
		//Definir servicios
		JadeUtils.registerService(this,
								  PlatformData.RESERVATION_ALIAS,
								  PlatformData.MAKE_RESERVATION_SER);
		
		//Definir comportamiento
		cB = new ReservationAgentCyclicBehaviour(this);
		addBehaviour(cB);
		
	}
}

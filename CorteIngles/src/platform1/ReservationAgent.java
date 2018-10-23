package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class ReservationAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setup()
	{
		JadeUtils.registerService(this,
								  PlatformData.RESERVATION_ALIAS,
								  PlatformData.MAKE_RESERVATION_SER);
		
		ReservationAgentCyclicBehaviour reservationBehaviour = new ReservationAgentCyclicBehaviour(this);
		this.addBehaviour(reservationBehaviour);
		
	}
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#takeDown()
	 */
	@Override
	protected void takeDown() {
		JadeUtils.deregisterService(this);
	}
}

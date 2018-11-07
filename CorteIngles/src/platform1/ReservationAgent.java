package platform1;

import jade.core.Agent;
import utilities.Debug;
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
		String[][] services = {{PlatformData.MAKE_RESERVATION_SER,
            					getLocalName()}};
		if (Debug.IS_ON)
			System.out.println("Reservation going to register\n");
		JadeUtils.registerServices(this, services);
		
		
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

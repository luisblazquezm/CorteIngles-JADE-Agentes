package reservation;

import jade.core.Agent;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

public class ReservationAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void setup()
	{
		try {
			PlatformUtils.registerAgentInPlatform(this, PlatformUtils.RESERVATION_AGENT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[][] services = {{PlatformUtils.MAKE_RESERVATION_SER,
            					getLocalName()}};
		Debug.message("Reservation going to register\n");
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

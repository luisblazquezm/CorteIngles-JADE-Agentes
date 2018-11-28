package reservation;

import jade.core.Agent;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class ReservationAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see jade.core.Agent#setup()
	 */
	@Override
	public void setup() {
		
		String[][] services = {{PlatformUtils.MAKE_RESERVATION_SER,
            					getLocalName()}};
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

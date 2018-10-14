package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class AgentAccomodation extends Agent
{
	protected CyclicBehaviourReservation cB;
	
	@Override
	public void setup()
	{
		//Definir servicios
		JadeUtils.registerService(this, "doReserve", "Accomodation");
		
		//Definir comportamiento
		cB = new CyclicBehaviourReservation(this);
		addBehaviour(cB);
		
	}
}

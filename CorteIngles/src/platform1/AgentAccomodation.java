package platform1;

import jade.core.Agent;

public class AgentAccomodation extends Agent
{
	protected CyclicBehaviourReservation cB;
	
	@Override
	public void setup()
	{
		//Definir servicios
		Utils.defineService(this, "doReserve", "Accomodation");
		
		//Definir comportamiento
		cB = new CyclicBehaviourReservation(this);
		addBehaviour(cB);
		
	}
}

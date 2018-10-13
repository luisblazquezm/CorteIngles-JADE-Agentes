package platform1;

import jade.core.Agent;

public class AgentUser extends Agent  
{
	protected CyclicBehaviourMakeTrip cB;
	
	@Override
	public void setup()
	{
		//Definir servicios
		Utils.defineService(this, "reserve", "User");
		
		//Definir comportamiento
		cB = new CyclicBehaviourMakeTrip(this);
		addBehaviour(cB);
		
	}
}

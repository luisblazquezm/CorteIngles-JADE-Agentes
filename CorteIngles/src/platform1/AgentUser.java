package platform1;

import jade.core.Agent;
import utilities.JadeUtils;

public class AgentUser extends Agent  
{
	protected CyclicBehaviourMakeTrip cB;
	
	@Override
	public void setup()
	{
		//Define servies
		JadeUtils.registerService(this, "reserve", "User");
		
		//Define behaviour
		cB = new CyclicBehaviourMakeTrip(this);
		addBehaviour(cB);
		
	}
}

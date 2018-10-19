/**
 * 
 */
package platform1;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * @author mrhyd
 *
 */
public class CorteInglesAgentServeReservationBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			
		}

	}

}

/**
 * 
 */
package platform1;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * @author mrhyd
 *
 */
public class CorteInglesAgentServeUserBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {
		
		/*
		 * 1- Get request from client
		 * 2- Send message to server
		 * 3- Wait for answer server (THIS IS THE KEY: it may be able to receive new requests)
		 * 4- Forward answer to client
		 */
		
		AID userAgentAid = new AID("user-agent", AID.ISLOCALNAME);
		AID reservationAgentAid = new AID("reservation-agent", AID.ISLOCALNAME);
		AID activityAgentAid = new AID("activity-agent", AID.ISLOCALNAME);
		
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			if (message.getSender().equals(userAgentAid)) {
				
				/*
				 * 1- Determine whether it relates to a reservation or an activity
				 * 2- Send appropriate message to corresponding agent
				 */
				
			} else if (message.getSender().equals(reservationAgentAid)) {
				
				/*
				 * Basically forward answer to user agent
				 */
				
			} else if (message.getSender().equals(activityAgentAid)) {
				
				/*
				 * Basically forward answer to user agent
				 */
				
			} else {
				// Should not happen but hey, just in case
				System.err.println("CorteInglesAgentServeUserBehaviour: unknown sender");
			}
		}
	}

}

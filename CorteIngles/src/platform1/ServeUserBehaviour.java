/**
 * @author mrhyd
 * 
 * This is one of the two subbehaviours of the agent named 'CorteIngles' in the task's PDF file.
 * Its cyclic functioning is:
 * 		1- Get REQUEST message (from UserAgent)
 * 		2- Differentiate between reservation and activity requests
 * 		3- Send request to ReservationAgent or ActivityAgent
 * 
 */

package platform1;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.Debug;
import utilities.JadeUtils;

/**
 * @author mrhyd
 *
 */
public class ServeUserBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void action() {
		
		// Get REQUEST message from UserAgent
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);

		if (message == null) {
			if (Debug.IS_ON)
				System.out.println("CorteInglesAgent blocked in ServeUserBehaviour");
			block();
		} else {

			try {
				
				if (Debug.IS_ON) {
					System.out.println(
						"ServeUserBehaviour: Message from "
						+ PlatformData.USER_ALIAS
						+ " was received at "
						+ PlatformData.CORTE_INGLES_ALIAS
					);
					
					System.out.print("ServeUserBehaviour: Request will be forwarded to ");
				}

				// Unwrap message content
				MessageContent<String> messageContent;
				AID userAid = message.getSender();
				
				messageContent = (MessageContent<String>) message.getContentObject();
				
				// Differentiate between reservation requests and activities requests
				if (messageContent.getRequestedService().equals(PlatformData.HANDLE_RESERVATION_SER)) {
					
					if(Debug.IS_ON) {
						System.out.println(PlatformData.RESERVATION_ALIAS);
					}
					
					// If reservation request, send REQUEST to ReservationAgent
					JadeUtils.sendMessage(
							this.myAgent,
							PlatformData.MAKE_RESERVATION_SER,
							ACLMessage.REQUEST,
							new IdentifiedMessageContent<String>(
								PlatformData.MAKE_RESERVATION_SER,
								messageContent.getData(),
								userAid
							)
					);
					
				} else if (messageContent.getRequestedService().equals(PlatformData.HANDLE_ACTIVITY_SER)) {
					
					if(Debug.IS_ON) {
						System.out.println(PlatformData.ACTIVITY_ALIAS);
					}
					
					// If activity request, send REQUEST to ActivityAgent
					int numberOfRecipients = JadeUtils.sendMessage(
						this.myAgent,
						PlatformData.RETRIEVE_ACTIVITY_SER, 
						ACLMessage.REQUEST,
						new IdentifiedMessageContent<String>(
							PlatformData.RETRIEVE_ACTIVITY_SER,
							messageContent.getData(),
							userAid
						)
					);
					
					if (Debug.IS_ON) {
						System.out.printf(
								"ServeUserBehaviour: message was forwarded to %d agents",
								numberOfRecipients
						);
					}
					
				} else {
					// Should not happen but hey, just in case
					System.err.println("ServeUserBehaviour: unknown request");
				}
				
			} catch (UnreadableException e) {
				System.err.println("ServeUserBehaviour: getContentObject failed");
				e.printStackTrace();
			}
		}
	}

}

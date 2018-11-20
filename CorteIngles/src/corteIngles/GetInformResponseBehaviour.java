/**
 * @author mrhyd
 * 
 * This is one of the two subbehaviours of the agent named 'CorteIngles' in the task's PDF file.
 * Its cyclic functioning is:
 * 		1- Receive INFORM message (from ActivityAgent or ReservationAgent)
 * 		2- Forward message (to UserAgent)
 * 
 */

package corteIngles;

import messages.ResponseMessageContent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author mrhyd
 *
 */
public class GetInformResponseBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	//@SuppressWarnings("unchecked")
	@Override
	public void action() {

		// Receive INFORM message from ActivityAgent or ReservationAgent
		Debug.message("GetInformResponseBehaviour: CorteInglesAgent waiting for INFORM message from Reservation/Activity\n");
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage message = this.myAgent.receive(template);
		Debug.message("GetInformResponseBehaviour: Message INFORM received in CorteInglesAgent\n");
		
		if (message == null) {
			Debug.message("GetInformResponseBehaviour: CorteInglesAgent blocked in ServeUserBehaviour");
			block();
		} else {
			
			try {
				
				if (message.getSender().equals(new AID(PlatformUtils.ACTIVITY_ALIAS, AID.ISLOCALNAME))) {
					Debug.message(
						"GetInformResponseBehaviour: "
						+ PlatformUtils.CORTE_INGLES_ALIAS
						+ " received INFORM type message from "
						+ PlatformUtils.ACTIVITY_ALIAS
					);
				} else {
					Debug.message(
							"GetInformResponseBehaviour: "
							+ PlatformUtils.CORTE_INGLES_ALIAS
							+ " received INFORM type message from "
							+ PlatformUtils.RESERVATION_ALIAS
						);
				}
				
				Debug.message(String.format(
					"GetInformResponseBehaviour: Message will be forwarded to "
					+ PlatformUtils.USER_ALIAS
					)
				);
				
				
				// Forward message to UserAgent
				ResponseMessageContent messageContent = (ResponseMessageContent) message.getContentObject();
				if (messageContent == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				//<------------------------------------------------------------------------- Identify the receiver of this message (UserAgent)
				messageContent.identify(message.getSender());
				
				if (messageContent.getRequester() == null)
					System.err.println("UserAgentCyclicBehaviour: requester is null");
				
				int numberOfRecipients = JadeUtils.sendMessage( // <--------------------- I think this won't work with more than one UserAgent
						this.myAgent,
		                PlatformUtils.HANDLE_USER_REQUEST_SER,
		                ACLMessage.INFORM,
		                messageContent);
				
				if (numberOfRecipients <= 0) {
					System.err.println("GetInformResponseBehaviour: no agents implementing requested service");
	        		return ;
				} 
				
				Debug.message(String.format(
							"GetInformResponseBehaviour: Message was forwarded to %d agents%n",
							numberOfRecipients
							)
				);
				
			} catch (UnreadableException e) {
				System.err.println("GetInformResponseBehaviour: getContentObject failed");
				e.printStackTrace();
			}
		}

	}

}

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

package corteIngles;

import data.ActivityRequestData;
import data.ReservationRequestData;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import messages.MessageContent;
import messages.Messages;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

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
	@Override
	public void action() {
		
		// Get REQUEST message from UserAgent
		Debug.message("ServeUserBehaviour: CorteInglesAgent waiting for REQUEST message from UserAgent\n");
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		Debug.message("ServeUserBehaviour: Message REQUEST received in CorteInglesAgent\n");

		if (message == null) {
			Debug.message("ServeUserBehaviour: CorteInglesAgent blocked in ServeUserBehaviour");
			block();
		} else {

			try {
				
				Debug.message(
					"ServeUserBehaviour: Message from "
					+ PlatformUtils.USER_ALIAS
					+ " was received at "
					+ PlatformUtils.CORTE_INGLES_ALIAS
				);
				
				Debug.message("ServeUserBehaviour: Request will be forwarded to ");
				
				// Unwrap message content
				MessageContent messageContent = (MessageContent) message.getContentObject();
				if (messageContent == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				// Identify the receiver of this message (Reservation or Activity)
				messageContent.identify(message.getSender());
				
				// Differentiate between reservation requests and activities requests
				if (messageContent.getService().equals(PlatformUtils.HANDLE_RESERVATION_SER)) {
					
					// If reservation request, send REQUEST to ReservationAgent
					Debug.message(PlatformUtils.RESERVATION_ALIAS);
					int numberOfRecipients = sendReservationDataMessage((ReservationRequestData) messageContent.getData());
					
					if (numberOfRecipients <= 0) {
						System.err.println("ServeUserBehaviour: no agents implementing requested service");
		        		return ;
					} 
					
				} else if (messageContent.getService().equals(PlatformUtils.HANDLE_ACTIVITY_SER)) {
					
					// If activity request, send REQUEST to ActivityAgent
					Debug.message(PlatformUtils.ACTIVITY_ALIAS);
					
					int numberOfRecipients = sendActivityReservationDataMessage((ActivityRequestData) messageContent.getData());
					
					if (numberOfRecipients <= 0) {
						System.err.println("ServeUserBehaviour: no agents implementing requested service");
		        		return ;
					} 

					Debug.message(String.format(
							"ServeUserBehaviour: message was forwarded to %d agents",
							numberOfRecipients)
					);
					
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

	private int sendReservationDataMessage(ReservationRequestData data) {
		return JadeUtils.sendMessage(
					this.myAgent,
					PlatformUtils.MAKE_RESERVATION_SER,
					ACLMessage.REQUEST,
					Messages.createCorteInglesToReservationMessageContent(data)
		);
		
	}
	
	private int sendActivityReservationDataMessage(ActivityRequestData data) {
		return JadeUtils.sendMessage(
						this.myAgent,
						PlatformUtils.RETRIEVE_ACTIVITY_SER, 
						ACLMessage.REQUEST,
						Messages.createCorteInglesToActivityMessageContent(data)
		);
		
	}
	
	

}

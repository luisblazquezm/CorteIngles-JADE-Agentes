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

import messages.ServiceDataPacket;
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
				
				if (message.getSender().equals(PlatformUtils.getAid(PlatformUtils.ACTIVITY_AGENT))) {
					Debug.message(
						"GetInformResponseBehaviour: "
						+ PlatformUtils.getLocalName(PlatformUtils.CORTE_INGLES_AGENT)
						+ " received INFORM type message from "
						+ PlatformUtils.getLocalName(PlatformUtils.ACTIVITY_AGENT)
					);
				} else {
					Debug.message(
							"GetInformResponseBehaviour: "
									+ PlatformUtils.getLocalName(PlatformUtils.CORTE_INGLES_AGENT)
									+ " received INFORM type message from "
									+ PlatformUtils.getLocalName(PlatformUtils.RESERVATION_AGENT)
						);
				}
				
				Debug.message(String.format(
					"GetInformResponseBehaviour: Message will be forwarded to "
					+ PlatformUtils.getLocalName(PlatformUtils.USER_AGENT)
					)
				);
				
				
				// Forward message to UserAgent
				ServiceDataPacket serviceDataPacket = (ServiceDataPacket) message.getContentObject();
				if (serviceDataPacket == null)
					System.err.println("UserAgentCyclicBehaviour: messageContent is null");
				
				int numberOfRecipients = JadeUtils.sendMessage(
						this.myAgent,
		                PlatformUtils.HANDLE_USER_REQUEST_SER,
		                ACLMessage.INFORM,
		                serviceDataPacket);
				
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

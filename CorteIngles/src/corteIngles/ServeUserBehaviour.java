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

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import packets.ServiceDataPacket;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
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
		
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		int numberOfRecipients = 0;

		if (message == null) {
			block();
		} else {

			try {

				ServiceDataPacket serviceDataPacket = (ServiceDataPacket) message.getContentObject();
				if (serviceDataPacket == null)
					System.err.println("ServeUserBehaviour: messageContent is null");
				
				if (serviceDataPacket.getService().equals(PlatformUtils.HANDLE_RESERVATION_SER)) {
					
					numberOfRecipients = JadeUtils.sendMessage(
							this.myAgent,
							PlatformUtils.MAKE_RESERVATION_SER,
							ACLMessage.REQUEST,
							serviceDataPacket
					);
					
				} else if (serviceDataPacket.getService().equals(PlatformUtils.HANDLE_ACTIVITY_SER)) {
					
					numberOfRecipients = JadeUtils.sendMessage(
							this.myAgent,
							PlatformUtils.RETRIEVE_ACTIVITY_SER,
							ACLMessage.REQUEST,
							serviceDataPacket
					);
					
				} else {
					// Should not happen but hey, just in case
					System.err.println("ServeUserBehaviour: unknown request");
				}
				
				if (numberOfRecipients <= 0) {
					System.err.println("ServeUserBehaviour: no agents implementing requested service");
				} 
				
			} catch (UnreadableException e) {
				System.err.println("ServeUserBehaviour: getContentObject failed");
				e.printStackTrace();
			}
		}
	}
}

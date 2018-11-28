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

		MessageTemplate template = PlatformUtils.createPlatformMessageTemplate(ACLMessage.INFORM);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			
			try {				
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
				
			} catch (UnreadableException e) {
				System.err.println("GetInformResponseBehaviour: getContentObject failed");
				e.printStackTrace();
			}
		}

	}

}

/**
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
public class GetInformResponseBehaviour extends CyclicBehaviour {

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

		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			
			try {
				
				if (Debug.IS_ON) {
					if (message.getSender().equals(new AID(PlatformData.ACTIVITY_ALIAS, AID.ISLOCALNAME))) {
						System.out.println(
							PlatformData.CORTE_INGLES_ALIAS
							+ " received INFORM type message from "
							+ PlatformData.ACTIVITY_ALIAS
						);
					} else {
						System.out.println(
								PlatformData.CORTE_INGLES_ALIAS
								+ " received INFORM type message from "
								+ PlatformData.RESERVATION_ALIAS
							);
					}
					
					System.out.println("Message will be forwarded to " + PlatformData.USER_ALIAS);
				}
				
				IdentifiedMessageContent<String> messageContent = (IdentifiedMessageContent<String>) message.getContentObject();
				JadeUtils.sendMessage(this.myAgent,
						              PlatformData.HANDLE_USER_REQUEST_SER,
						              messageContent);
				
				if (Debug.IS_ON) {
					System.out.println("Message was forwarded");
				}
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

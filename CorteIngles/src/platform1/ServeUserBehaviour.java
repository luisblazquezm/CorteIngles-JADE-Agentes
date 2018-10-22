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
				
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			
			try {
				
				if (Debug.IS_ON) {
					System.out.println(
						"Message from "
						+ PlatformData.USER_ALIAS
						+ " was received at "
						+ PlatformData.CORTE_INGLES_ALIAS
					);
					
					System.out.print("Request will be forwarded to ");
				}

				MessageContent<String> messageContent;
				AID userAid = message.getSender();
				
				messageContent = (MessageContent<String>) message.getContentObject();
				
				if (messageContent.getRequestedService().equals(PlatformData.HANDLE_RESERVATION_SER)) {
					
					if(Debug.IS_ON) {
						System.out.println(PlatformData.RESERVATION_ALIAS);
					}
					
					JadeUtils.sendMessage(
							this.myAgent,
							PlatformData.MAKE_RESERVATION_SER, 
							new IdentifiedMessageContent<String>(
								PlatformData.MAKE_RESERVATION_SER,
								messageContent.getData(),
								userAid
							)
					);
					
				} else if (messageContent.getRequestedService().equals(PlatformData.HANDLE_ACTIVITY_SER)) {
					
					if(Debug.IS_ON) {
						System.out.println(PlatformData.RESERVATION_ALIAS);
					}
					
					JadeUtils.sendMessage(
							this.myAgent,
							PlatformData.RETRIEVE_ACTIVITY_SER, 
							new IdentifiedMessageContent<String>(
								PlatformData.RETRIEVE_ACTIVITY_SER,
								messageContent.getData(),
								userAid
							)
					);
					
				} else {
					// Should not happen but hey, just in case
					System.err.println("ServeUserBehaviour: unknown sender");
				}
				
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

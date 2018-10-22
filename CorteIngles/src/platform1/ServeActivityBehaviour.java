/**
 * 
 */
package platform1;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.JadeUtils;

/**
 * @author mrhyd
 *
 */
public class ServeActivityBehaviour extends CyclicBehaviour {

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
				
				IdentifiedMessageContent<String> messageContent = (IdentifiedMessageContent<String>) message.getContentObject();

				JadeUtils.sendMessage(this.myAgent,
						              PlatformData.HANDLE_USER_REQUEST_SER,
						              messageContent);
				
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

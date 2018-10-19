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

		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			IdentifiedMessageContent<String> messageContent;
			try {
				messageContent = new IdentifiedMessageContent<>(
					(IdentifiedMessageContent<String>) JadeUtils.extractMessageContent(message),
					message.getSender()
				);

				JadeUtils.sendMessage(this.myAgent,
						              PlatformData.RETRIEVE_ACTIVITY_SER,
						              messageContent);
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

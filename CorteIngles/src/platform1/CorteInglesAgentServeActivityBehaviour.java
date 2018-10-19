/**
 * 
 */
package platform1;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import utilities.JadeUtils;

/**
 * @author mrhyd
 *
 */
public class CorteInglesAgentServeActivityBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() {

		MessageTemplate template = 
				MessageTemplate.and(
						MessageTemplate.MatchPerformative(ACLMessage.INFORM),
						MessageTemplate.MatchSender(
								new AID(PlatformData.ACTIVITY_ALIAS, AID.ISLOCALNAME)
						)
		        );
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
			IdentifiedMessageContent<String> messageContent =
					new IdentifiedMessageContent<>(
							JadeUtils.extractMessageContent(message),
							message.getSender()
					);
			JadeUtils.sendMessage(message.get,
					              serviceType,
					              object)
		}

	}

}

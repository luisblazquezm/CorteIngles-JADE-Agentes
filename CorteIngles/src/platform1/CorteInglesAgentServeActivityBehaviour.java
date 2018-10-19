/**
 * 
 */
package platform1;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

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

		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage message = this.myAgent.receive(template);
		
		if (message == null) {
			block();
		} else {
<<<<<<< HEAD
			IdentifiedMessageContent<String> messageContent;
			try {
				messageContent = new IdentifiedMessageContent<>(
						JadeUtils.extractMessageContent(message),
						message.getSender()
				);

				JadeUtils.sendMessage(this.myAgent,
						              PlatformData.RETRIEVE_ACTIVITY_SER,
						              messageContent);
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
=======
			// Handle message
>>>>>>> 3ac555aeb7616db17b4a70355f83f35bc2180d73
		}

	}

}

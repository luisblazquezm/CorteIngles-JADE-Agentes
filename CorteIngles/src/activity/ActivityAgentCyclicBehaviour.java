/**
 * @author mrhyd
 * 
 * This class is the behaviour of the agent named 'Ocio' in the task's PDF file.
 * Its cyclic functioning is:
 * 		1- Wait for REQUEST message (must be sent from CorteInglesAgent)
 * 		2- Look for coincidences in the activities list of city in Data class
 * 		3- Send INFORM message (to CorteInglesAgent)
 * 		4- Go back to point #1
 * 
 */

package activity;

import java.util.List;
import data.Activity;
import data.ActivityInformData;
import data.ActivityRequestData;
import data.Data;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import packets.Packets;
import packets.ServiceDataPacket;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author Luis Blázquez Miñambres y Samuel Gómez Sánchez
 *
 */
public class ActivityAgentCyclicBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param agent This object's agent
	 */
	public ActivityAgentCyclicBehaviour(Agent agent) {
		super(agent);
	}
	
	
	/**
	 * 
	 */
	public ActivityAgentCyclicBehaviour() {
		super();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() 
	{
		
		MessageTemplate template = PlatformUtils.createPlatformMessageTemplate(ACLMessage.INFORM);
		ACLMessage msg = this.myAgent.receive(template);
				
		if (msg == null) {
			block();
		} else {
			try {				
				ServiceDataPacket requestPacket = (ServiceDataPacket) msg.getContentObject();
				ActivityRequestData data = (ActivityRequestData)requestPacket.getData();
				List<Activity> activities = Data.getActivities(data.getCity(), data.getStartDate());
				
				if (activities == null) {
					System.err.println("ActivityAgentCyclicBehaviour: listOfActivies is null");
				} else {
					ActivityInformData informData = new ActivityInformData(PlatformUtils.ACTIVITY_AGENT, data.getCity(), activities);
					
					ServiceDataPacket answerMessageContent = Packets.createActivityInformServiceDataPacket(requestPacket, informData);

					int numberOfRecipients = JadeUtils.sendMessage(this.myAgent,
																  PlatformUtils.HANDLE_ACTIVITY_SER,
																  ACLMessage.INFORM,
																  answerMessageContent);
					
					if (numberOfRecipients <= 0) {
						System.err.println("ServeUserBehaviour: no agents implementing requested service");
		        		return;
					} 
				}
			} catch (UnreadableException e) {
				System.err.println("ActivityAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}
	}
}

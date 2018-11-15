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
import java.util.regex.Pattern;

import data.Activity;
import data.Data;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import messages.IdentifiedMessageContent;
import messages.MessageContent;
import utilities.Debug;
import utilities.JadeUtils;
import utilities.PlatformUtils;

/**
 * @author mrhyd
 *
 */
public class ActivityAgentCyclicBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ActivityAgentCyclicBehaviour(Agent agent) {
		super(agent);
	}
	
	public ActivityAgentCyclicBehaviour() {
		super();
	}

	/* (non-Javadoc)
	 * @see jade.core.behaviours.Behaviour#action()
	 */
	@Override
	public void action() 
	{
		
		String answerMessageContentData;

		// Waiting for a REQUEST message from AgentCorteIngles to do an activity
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
				
		if (msg == null) {
			block();
		} else {
			try
			{
				@SuppressWarnings("unchecked")
				MessageContent<String> content = (MessageContent<String>) msg.getContentObject();
				String data = content.getData();
				answerMessageContentData = this.getActivitiesString(data);
				IdentifiedMessageContent<String> answerMessageContent =
						new IdentifiedMessageContent<>(PlatformUtils.HANDLE_ACTIVITY_SER,
						answerMessageContentData,
						this.myAgent);
				
		    	//INFORM MESSAGE ELABORATION
				if (Debug.IS_ON) {
					System.out.println("ActivityAgent: REQUEST received from AgentCorteIngles\n");
				}
				
		    	//INFORM MESSAGE SENDING 
				JadeUtils.sendMessage(this.myAgent,
									  PlatformUtils.HANDLE_ACTIVITY_SER,
									  ACLMessage.INFORM,
									  answerMessageContent);
				if (Debug.IS_ON) {
					System.out.println("ActivityAgent: INFORM message sent");
				}
			}
			catch (UnreadableException e)
			{
				System.err.println("ActivityAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}
	}

	private String getActivitiesString(String dataString) {
		
		// 'dataString' should be "City#Date"
		String[] data = dataString.split(Pattern.quote(PlatformUtils.DELIMITER));
		
		List<Activity> activities = Data.getActivities(data[0], Integer.parseInt(data[1]));
		if (activities == null) {
			return PlatformUtils.ACTIVITY_MESSAGE + PlatformUtils.DELIMITER + "None";
		}
		
		StringBuilder answer = new StringBuilder();
		
		answer.append(PlatformUtils.ACTIVITY_MESSAGE);
		for (Activity a : activities) {
			answer.append(PlatformUtils.DELIMITER);
			answer.append(delimitedStringFromActivity(a));
		}
		
		return new String(answer);
	}
	
	private String delimitedStringFromActivity(Activity activity) {
		
		int[] schedule = activity.getScheduleDescription();
		
		StringBuilder string = new StringBuilder();
		
		string.append(activity.getName());
		string.append(PlatformUtils.ACTIVITIES_DELIMITER);
		string.append(String.valueOf(schedule[0]));
		string.append(PlatformUtils.ACTIVITIES_DELIMITER);
		string.append(String.valueOf(schedule[1]));
		
		return new String(string);
	}

}

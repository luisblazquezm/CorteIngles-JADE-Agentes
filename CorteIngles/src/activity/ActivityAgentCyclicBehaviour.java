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
import messages.MessageContent;
import messages.Messages;
import messages.ResponseMessageContent;
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
	@SuppressWarnings("unused")
	@Override
	public void action() 
	{
		
		// Waiting for a REQUEST message from AgentCorteIngles to do an activity
		Debug.message("ActivityAgentCyclicBehaviour: CorteInglesAgent waiting for REQUEST message from UserAgent\n");
		MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		ACLMessage msg = this.myAgent.receive(template);
		Debug.message("ActivityAgentCyclicBehaviour: Message REQUEST received in CorteInglesAgent\n");
				
		if (msg == null) {
			block();
		} else {
			try
			{
				Debug.message("ActivityAgentCyclicBehaviour: REQUEST received from AgentCorteIngles\n");
				
				MessageContent content = (MessageContent) msg.getContentObject();
				ActivityRequestData data = (ActivityRequestData)content.getData();
				List<Activity> activities = Data.getActivities(data.getCity(), data.getStartDate());
				
				if (activities == null) {
					System.err.println("ActivityAgentCyclicBehaviour: listOfActivies is null");
				} else {
					ActivityInformData informData = new ActivityInformData(activities);
					if (informData == null)
						System.err.println("ActivityAgentCyclicBehaviour: informData is null");
					
					ResponseMessageContent answerMessageContent = Messages.createActivityInformMessageContent(content, informData);
					if (answerMessageContent == null)
						System.err.println("ActivityAgentCyclicBehaviour: answerMessageContent is null");
					
			    	//INFORM MESSAGE ELABORATION
					Debug.message("ActivityAgent: REQUEST received from AgentCorteIngles\n");
					
			    	//INFORM MESSAGE SENDING 
					int numberOfRecipients = JadeUtils.sendMessage(this.myAgent,
																  PlatformUtils.HANDLE_ACTIVITY_SER,
																  ACLMessage.INFORM,
																  answerMessageContent);
					
					if (numberOfRecipients <= 0) {
						System.err.println("ServeUserBehaviour: no agents implementing requested service");
		        		return ;
					} 
					
					Debug.message("ActivityAgent: INFORM message sent");
				}
			}
			catch (UnreadableException e)
			{
				System.err.println("ActivityAgentCyclicBehaviour: getContentObject failed");
				e.printStackTrace();
			}
			
		}
	}

	// I think they´re useless
	
	/*
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
	 */
}

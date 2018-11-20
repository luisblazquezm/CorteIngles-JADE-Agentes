/**
 * @author mrhyd
 * 
 * This class provides the user with a set of static methods
 * that implement different utilities for the JADE RTE
 */

package utilities;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.regex.Pattern;
import utilities.Debug;


public class JadeUtils
{
	/**
	 * @param data
	 * @return
	 */
	public static String[] getReservationData(String data) {
		
		return data.split(Pattern.quote(PlatformUtils.DELIMITER));
	}
		
	/**
	 * @param availability
	 * @return
	 */
	public static String delimitedStringFromReservation(boolean availability) {
			
		StringBuilder string = new StringBuilder();
		
		string.append(PlatformUtils.RESERVATION_MESSAGE);
		string.append(PlatformUtils.DELIMITER);
		
		if (availability){
			string.append(PlatformUtils.RESERVATION_AVAILABLE);
			string.append(PlatformUtils.DELIMITER);
		} else {
			string.append(PlatformUtils.RESERVATION_NOT_AVAILABLE);
			string.append(PlatformUtils.DELIMITER);
		}
		
		string.append(PlatformUtils.SENDER_CITY_INDEX);
		string.append(PlatformUtils.DELIMITER);
		string.append(PlatformUtils.SENDER_HOTEL_INDEX);
		string.append(PlatformUtils.DELIMITER);
		string.append(PlatformUtils.SENDER_DEPARTURE_INDEX);
		string.append(PlatformUtils.DELIMITER);
		string.append(PlatformUtils.SENDER_RETURN_INDEX); 
		
		return new String(string);
	}
	
	// Este método está bien, calcado del manual de JADE
	/**
	 * Look for all agents providing a service.
	 * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @return DFAgentDescription array of agents which provide the service.
	 */
    protected static DFAgentDescription[] findAgentsForService(Agent clientAgent, String serviceType)
    {
    	DFAgentDescription template = new DFAgentDescription();
    	ServiceDescription serviceDescription = new ServiceDescription();
    	serviceDescription.setType(serviceType);
    	template.addServices(serviceDescription);
    	
    	try {
    		DFAgentDescription[] result = DFService.search(clientAgent, template);
    		if (Debug.IS_ON) {
	    		System.out.printf(
	    				"JadeUtils: findAgentsForService: REQUESTED SERVICE %s by %s -> N_MATCHES %d%n",
	    				serviceType,
	    				clientAgent.getLocalName(),
	    				result.length);
    		}
    		return result;
    	} catch(FIPAException e) {
    		
//			JOptionPane.showMessageDialog(null, 
//				  "findAgentsForService: Agent " + getLocalName() + ": " + e.getMessage(),
//			"Error",
//			JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
		}
        
        return null;
    }
    
    // Este método está bien, calcado del manual de JADE
    /**
     * Look for all agents providing a service and get the first of them.
     * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @return First agent which provides the service.
     */
    protected static DFAgentDescription findFirstAgentForService(Agent clientAgent, String serviceType)
    {
    	DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceType);
        template.addServices(serviceDescription);
        
        SearchConstraints constraints = new SearchConstraints();
        constraints.setMaxResults(new Long(1));
        
        try {
            DFAgentDescription [] results = DFService.search(clientAgent, template, constraints);
            if (results.length > 0) {
                //System.out.println("findFirstAgentForService: Agent " + agent.getLocalName() + " found following agents: ");
                for (int i = 0; i < results.length; ++i) {
                	
                    DFAgentDescription dfd = results[i];
                    
                    // Not used, wtf? This guys gives us this shitty code...
//                    AID provider = dfd.getName();
                    
                    @SuppressWarnings("unchecked")
					Iterator<ServiceDescription> iterator = dfd.getAllServices();
                    
                    while (iterator.hasNext()) {
                        ServiceDescription sd = (ServiceDescription) iterator.next();
                        if (sd.getType().equals(serviceType)) {
                            //System.out.println("findFirstAgentForService: Service \"" + sd.getName() + "\" is provided by agent " + provider.getName());
                            return dfd;
                        }
                    }
                }
            } else {
//                JOptionPane.showMessageDialog(null,
//                							  "Agent " + getLocalName() + " did not found any service",
//                							  "Error",
//                							  JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(FIPAException e) {
//            JOptionPane.showMessageDialog(null,
//        	                              "Agente " + getLocalName() + ": " + e.getMessage(),
//        	                              "Error",
//        	                              JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
        
        return null;
    }
    
    // Este método está bien, calcado del manual de JADE
    /**
     * Send an object from agent clientAgent to an agent implementing specified service.
     * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @param performative ACLMessage performative for the message
	 * @param object Sent message.
     */
    public static int sendMessage(Agent clientAgent, String serviceType, int performative , Object object)
    {
        DFAgentDescription[] dfd;
        dfd = findAgentsForService(clientAgent, serviceType);
        
        try {
            if(dfd != null) {
            	
            	ACLMessage aclMessage = new ACLMessage(performative);
            	
            	for(int i = 0; i < dfd.length; ++i) {
	        		aclMessage.addReceiver(dfd[i].getName());
            	}
            	
                aclMessage.setOntology("ontology");
                aclMessage.setLanguage(new SLCodec().getName());
                aclMessage.setEnvelope(new Envelope());
				aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
                //aclMessage.getEnvelope().setAclRepresentation(FIPANames.ACLCodec.XML);
				aclMessage.setContentObject((Serializable) object);
        		clientAgent.send(aclMessage);       		
            }
            
        } catch(IOException e) {
//            JOptionPane.showMessageDialog(null,
//                                        "Agente " + getLocalName() + ": " + e.getMessage(),
//                                        "Error",
//                                        JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
        }
        
        return dfd.length;
    }

    // Este método está bien, calcado del manual de JADE
    /**
     * Defines a service that will be implemented by one or more agents.
     * @param agent Agent that implements the service.
	 * @param services Array of Service's type - Service's name pairs
     */
    public static void registerServices(Agent agent, String[][] services)
    	throws NullPointerException
    {
    	
    	if (agent == null || services == null) {
    		throw new NullPointerException();
    	}
    	
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(agent.getAID());
		
		for (int i = 0; i < services.length; ++i) {
			ServiceDescription sd = new ServiceDescription();
			sd.setType(services[i][0]);
			sd.setName(services[i][1]);
//			sd.addOntologies("ontologia");
//			sd.addLanguages(new SLCodec().getName());
			dfd.addServices(sd);
		}
		
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public static void deregisterService (Agent agent) 
    		throws NullPointerException
    {
    	if (agent == null) {
    		throw new NullPointerException();
    	}
    	
    	try {
    		DFService.deregister(agent);
    	} catch (FIPAException e) {
    		e.printStackTrace();
    	}
    		
    }

    /**
     * Prompts a table of available cities
     * @param title Header of the table 
	 * @param cityNames Array of city names
	 * @param width Size of width of the table.
     */
	public static void printStringTable(String title, String[] cityNames, int width) {
		final String CITY_NAMES_AVAILABILITY_ERROR = "No hay ciudades disponibles";
		final String NEW_LINE = "\n";
		final String TABLE_JOINT_SYMBOL = "+";
		final String TABLE_V_SPLIT_SYMBOL = "|";
		final String TABLE_H_SPLIT_SYMBOL = "-";
		final String TABLE_H_SPACE_SYMBOL = " ";
		StringBuilder sb = new StringBuilder();
		
		if (cityNames == null) {
			System.out.println(CITY_NAMES_AVAILABILITY_ERROR);
		} else {
			
			if (width <= 0) {
				System.out.println("JadeUtils: incorrect table width");
			} else {
				
				if (title == null) {
					System.out.println("JadeUtils: table header is empty");
				} else {
					sb.append(NEW_LINE + TABLE_JOINT_SYMBOL);
			    	for (int i = 0 ; i < width ; i++)
				    	sb.append(TABLE_H_SPLIT_SYMBOL);
				    sb.append(TABLE_JOINT_SYMBOL + NEW_LINE);
				    
				    sb.append(TABLE_V_SPLIT_SYMBOL + title);
				    for (int i = title.length() ; i < width ; i++)
				    	sb.append(TABLE_H_SPACE_SYMBOL);
				    sb.append(TABLE_V_SPLIT_SYMBOL + NEW_LINE);
				    
					sb.append(TABLE_JOINT_SYMBOL);
			    	for (int i = 0 ; i < width ; i++)
				    	sb.append(TABLE_H_SPLIT_SYMBOL);
				    sb.append(TABLE_JOINT_SYMBOL + NEW_LINE);
				    
				    for(String c : cityNames) {
						sb.append(TABLE_V_SPLIT_SYMBOL + c);
				    	for (int i = c.length() ; i < width ; i++)
					    	sb.append(TABLE_H_SPACE_SYMBOL);
					    sb.append(TABLE_V_SPLIT_SYMBOL + NEW_LINE);
				    }
				    
				    sb.append(TABLE_JOINT_SYMBOL);
			    	for (int i = 0 ; i < width ; i++)
				    	sb.append(TABLE_H_SPLIT_SYMBOL);
				    sb.append(TABLE_JOINT_SYMBOL + NEW_LINE);
				    
				    System.out.printf(sb.toString());
				}
			}
		}
	}
}

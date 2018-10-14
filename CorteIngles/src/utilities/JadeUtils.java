/* This class provides the user with a set of static methods
 * that implement different utilities for the JADE RTE
 * 
 * TODO:
 * - Change package when added to new project
 */
package utilities;

import jade.content.lang.sl.SLCodec;
import jade.core.AID;
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

public class JadeUtils
{
	
	/**
	 * Look for all agents providing a service.
	 * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @return DFAgentDescription array of agents which provide the service.
	 */
    protected static DFAgentDescription [] findAgentsForService(Agent clientAgent, String serviceType)
    {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(serviceType);
        template.addServices(serviceDescription);
        
        SearchConstraints constraints = new SearchConstraints();
        constraints.setMaxResults(Long.MAX_VALUE);
        
        try {
            DFAgentDescription [] results = DFService.search(clientAgent, template, constraints);
            return results;
        } catch(FIPAException e) {
//            JOptionPane.showMessageDialog(null, 
//        								  "findAgentsForService: Agent " + getLocalName() + ": " + e.getMessage(),
//        	                              "Error",
//        	                              JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
        }
        
        return null;
    }
    
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
                    AID provider = dfd.getName();
                    
                    Iterator iterator = dfd.getAllServices();
                    
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
    
    /**
     * Send an object from agent clientAgent to an agent implementing specified service.
     * @param clientAgent Agent that requests service.
	 * @param serviceType  Required service's type.
	 * @param object Sent message.
     */
    public static void sendMessage(Agent clientAgent, String serviceType, Object object)
    {
        DFAgentDescription[] dfd;
        dfd = findAgentsForService(clientAgent, serviceType);
        
        try {
            if(dfd != null) {
            	
            	ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
            	
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
    }
    
    /**
     * Defines a service that will be implemented by one or more agents.
     * @param agent Agent that implements the service.
	 * @param serviceType New service's type.
	 * @param serviceName New service's name.
     */
    public static void registerService(Agent agent, String serviceType, String serviceName)
    {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(agent.getAID());
		
		ServiceDescription sd = new ServiceDescription();
		sd.setName(serviceName);
		sd.setType(serviceType);
		sd.addOntologies("ontologia");
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);
		
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }


}

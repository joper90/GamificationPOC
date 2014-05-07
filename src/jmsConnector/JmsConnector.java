package jmsConnector;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.xmlbeans.XmlException;

import parsers.EventParser;

import com.ibm.ac.commonbaseevent101.CommonBaseEventsDocument;
import common.StaticsCommon;
import engine.AchivementProcessor;



public class JmsConnector implements  ExceptionListener, MessageListener {

	private String				serverUrl	= StaticsCommon.JMS_SERVER;

	private String				userName	= StaticsCommon.JMS_USER;

	private String				password	= StaticsCommon.JMS_PASSWORD;

	private String				topicName		= StaticsCommon.JMS_TOPIC;
	
	public boolean connectAndSpawn() 
	{

		System.out.println("Connecting to jms : " + serverUrl + " " + userName + " " + topicName);

		try
		{
			TopicConnectionFactory factory = new com.tibco.tibjms.TibjmsTopicConnectionFactory(serverUrl);

            TopicConnection connection = factory.createTopicConnection(userName,password);

            TopicSession session = connection.createTopicSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
            
            javax.jms.Topic topic = session.createTopic(topicName);

            TopicSubscriber subscriber = session.createSubscriber(topic);
            
            subscriber.setMessageListener(this);
            
            connection.start();
            
		}
		catch (Exception e)
		{
			System.out.println("Connection failed.. ");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public void onMessage(Message message) {
		System.out.println("Got a message [GameificationPOC Server]" );
		try {
			String text = ((com.tibco.tibjms.TibjmsTextMessage) message).getText();
			//System.out.println(text);
					
			CommonBaseEventsDocument cbeDoc = CommonBaseEventsDocument.Factory.parse(text);
			EventParser eventParser = new EventParser(cbeDoc.getCommonBaseEvents());
			eventParser.process();
			
			//Cheap shouldn't procss this here really.. But as its a POC..
			AchivementProcessor aParser = new AchivementProcessor(eventParser);
			aParser.digest();
			
			
		} catch (JMSException | XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onException(JMSException e) {
		/* print the connection exception status */
		System.err.println("CONNECTION EXCEPTION: " + e.getMessage());
		
	}
	
}

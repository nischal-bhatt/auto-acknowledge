package com.nish.jms.guaranteedmessaging;


import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;



public class MessageProducer {

	public static void main(String[] args) throws NamingException, JMSException {

		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueueNishMsgGuarantee12");
		//Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueueNish123");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
			//	JMSContext jmsContext = cf.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
			      JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED)){
				JMSProducer producer = jmsContext.createProducer();
			producer.send(requestQueue, "mssage 1");
			producer.send(requestQueue, "mssage 2");
			jmsContext.commit();
			
		}
		;
	}

}

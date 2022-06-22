
package com.nish.jms.guaranteedmessaging;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;



public class MessageConsumer {

	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {

		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueueNishMsgGuarantee12");
		//Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueueNish123");

		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext()) {
			
			
			JMSConsumer consumer = jmsContext.createConsumer(requestQueue);
			
			TextMessage receive = (TextMessage) consumer.receive();
		    System.out.println(receive.getText());
		    Thread.sleep(10000);
		}
		;
	}

}

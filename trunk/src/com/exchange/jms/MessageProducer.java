package com.exchange.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class MessageProducer {
	public JmsTemplate getSender() {
		return sender;
	}
	public void setSender(JmsTemplate sender) {
		this.sender = sender;
	}
	public Queue getQueue() {
		return queue;
	}
	public void setQueue(Queue queue) {
		this.queue = queue;
	}
	private JmsTemplate sender;
	private Queue queue;
	public void send(final String message){
		sender.send(queue, new MessageCreator(){

			@Override
			public Message createMessage(Session session) throws JMSException {
				Message msg=session.createTextMessage(message);
				return msg;
			}});
	}
}

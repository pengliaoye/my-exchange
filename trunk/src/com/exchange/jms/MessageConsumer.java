package com.exchange.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

public class MessageConsumer implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		System.out.println(arg0);
	}
}

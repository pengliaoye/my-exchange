package com.exchange.loggingmodule;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.handlers.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHandler extends AbstractHandler{
	
	private static final Logger logger=LoggerFactory.getLogger(LogHandler.class);

	@Override
	public InvocationResponse invoke(MessageContext msgContext)
			throws AxisFault {
		logger.info(msgContext.getEnvelope().toString());
		return InvocationResponse.CONTINUE;
	}

}

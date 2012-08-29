package com.exchange.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.el.Expression;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class WsDelegate implements JavaDelegate {

	private Expression wsdl;
	private Expression operation;
	private Expression parameters;
	private Expression returnValue;

	public void execute(DelegateExecution execution) throws Exception {
		String wsdlUrl = (String) wsdl.getValue(execution);
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(wsdlUrl);
		String params = (String) parameters.getValue(execution);
		Object response = client.invoke((String) operation.getValue(execution),
				new Object[] { params });
		if (returnValue != null) {
			String returnVariableName = (String) returnValue
					.getValue(execution);
			execution.setVariable(returnVariableName, response);
		}
	}

}

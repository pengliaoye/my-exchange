package com.exchange.loggingmodule;

import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisDescription;
import org.apache.axis2.description.AxisModule;
import org.apache.axis2.modules.Module;
import org.apache.neethi.Assertion;
import org.apache.neethi.Policy;

public class LoggingModule implements Module{

	@Override
	public void applyPolicy(Policy policy, AxisDescription axisDescription)
			throws AxisFault {
		
	}

	@Override
	public boolean canSupportAssertion(Assertion assertion) {
	
		return false;
	}

	@Override
	public void engageNotify(AxisDescription axisDescription) throws AxisFault {
	
	}

	@Override
	public void init(ConfigurationContext configContext, AxisModule module)
			throws AxisFault {
		
	}

	@Override
	public void shutdown(ConfigurationContext configurationContext)
			throws AxisFault {
		
	}

}

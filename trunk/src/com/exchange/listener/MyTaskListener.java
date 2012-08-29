package com.exchange.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class MyTaskListener implements TaskListener {

	public void notify(DelegateTask delegateTask) {
		delegateTask.getExecution().setVariable("recipient", "pengguoyong@oraro.net");
	}
}

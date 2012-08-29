package com.exchange.workflow;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.bpmn.behavior.ReceiveTaskActivityBehavior;
import org.activiti.engine.impl.pvm.ProcessDefinitionBuilder;
import org.activiti.engine.impl.pvm.PvmExecution;
import org.activiti.engine.impl.pvm.PvmProcessDefinition;
import org.activiti.engine.impl.pvm.PvmProcessInstance;
import org.activiti.engine.impl.test.PvmTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.exchange.domain.DataInputOfProcess;
import com.exchange.domain.DataOutputOfProcess;

/**
 * @author zxc10
 */
public class WorkFlowTest extends PvmTestCase {
	/**
	 */
	private ProcessEngine processEngine;

	/**
	 */
	@Before
	public void setUp() {
		processEngine = ProcessEngines.getDefaultProcessEngine();
	}

	/**
	 */
	@Test
	public void testDeploy() {
		Deployment deployment = processEngine.getRepositoryService()
				.createDeployment().name("workflow").addClasspathResource(
						"workflow.bpmn20.xml").deploy();
		Assert.assertNotNull(deployment);
	}

	@Test
	public void testHistoryService() {
		HistoryService historyService = processEngine.getHistoryService();
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey("20110319022118").singleResult();
		Assert.assertEquals(historicProcessInstance.getProcessDefinitionId(),
				"workflow:1:3");
	}

	@Test
	public void testProcessInstance() {
		String processDefinitionKey = "workflow";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String businessKey = format.format(new Date());
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("flowno", businessKey);
		String request = "123456";
		DataInputOfProcess dataInputOfProcess = new DataInputOfProcess();
		dataInputOfProcess.setRequest(request);
		variables.put("dataInputOfProcess", dataInputOfProcess);
		DataOutputOfProcess dataOutputOfProcess = new DataOutputOfProcess();
		variables.put("dataOutputOfProcess", dataOutputOfProcess);
		variables.put("request", "test");
		String authenticatedUserId = "kermit";
		processEngine.getIdentityService().setAuthenticatedUserId(
				authenticatedUserId);
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						variables);
		Assert.assertNotNull(processInstance);
	}

	@Test
	public void testIdentity() {
		processEngine.getIdentityService().saveUser(
				processEngine.getIdentityService().newUser("kermit"));
		processEngine.getIdentityService().saveGroup(
				processEngine.getIdentityService().newGroup("user"));
		processEngine.getIdentityService().createMembership("kermit", "user");

		processEngine.getIdentityService().saveGroup(
				processEngine.getIdentityService().newGroup("management"));
	}

	@Test
	public void testTask() {
		List<Task> tasks = processEngine.getTaskService().createTaskQuery()
				.taskCandidateUser("kermit").list();
		Assert.assertEquals(tasks.size(), 1);
		processEngine.getTaskService().claim(tasks.get(0).getId(), "kermit");
		processEngine.getTaskService().complete(tasks.get(0).getId());
	}

	@Test
	public void testVariables() {
		ProcessInstance processInstance = processEngine.getRuntimeService()
				.createProcessInstanceQuery().processInstanceBusinessKey(
						"20110328174438").singleResult();
		Map<String, Object> variables = processEngine.getRuntimeService()
				.getVariables(processInstance.getId());
		DataOutputOfProcess dataOutputOfProcess = (DataOutputOfProcess) variables
				.get("dataOutputOfProcess");
		System.out.println(dataOutputOfProcess.getServiceReturn());
		Object[] returnValues = (Object[]) variables.get("serviceReturn");
		System.out.println(returnValues[0]);
	}

	@Test
	public void testPvm() {
		PvmProcessDefinition processDefinition = new ProcessDefinitionBuilder()
				.createActivity("a").initial().behavior(
						new ReceiveTaskActivityBehavior()).transition("b")
				.endActivity().buildProcessDefinition();
		PvmProcessInstance processInstance = processDefinition
				.createProcessInstance();
		processInstance.start();
		PvmExecution activityInstance = processInstance.findExecution("a");
		Assert.assertNotNull(activityInstance);
		activityInstance.signal(null, null);
		activityInstance = processInstance.findExecution("b");
		Assert.assertNotNull(activityInstance);
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
}

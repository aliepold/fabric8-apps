package de.aliepold.fabric8.bpmn.example;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = de.aliepold.fabric8.bpmn.example.CamundaSpringBootExampleApplication.class)
public class CamundaSpringBootExampleApplicationTest {

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

	@Test  
	public void verifyProcessInstanceStarted() {
	  // process instance is started by the application and wait on the user task
	  Task task = taskService.createTaskQuery().taskName("Simple User Task").singleResult();
	  assertThat(task, is(notNullValue()));

	  // complete the user task and verify that the process ends
	  taskService.complete(task.getId());

	  assertThat(runtimeService.createProcessInstanceQuery().count(), is(0L));
	}

}
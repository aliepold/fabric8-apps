package de.aliepold.fabric8.jpa.mongodb;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = de.aliepold.fabric8.jpa.mongodb.JpaMongoDbApplication.class)
public class JpaMongoDbApplicationTest {

	@Test
	public void testOk() {

		assertTrue(true);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void editYamlLambdaStyle() throws Exception {

		List<String> excludeTriggerForContainer = asList("nginx-proxy", "mongodb");

		File deploymentFile = new File("target/classes/META-INF/fabric8/openshift.yml");

		if (deploymentFile.exists()) {

			DumperOptions options = new DumperOptions();
			options.setIndent(2);
			options.setPrettyFlow(true);
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

			Yaml yaml = new Yaml(options);

			FileInputStream inputStream = new FileInputStream(deploymentFile);

			Map<String, Object> obj = (Map<String, Object>) yaml.load(inputStream);
			List<Map<String, Object>> items = (List<Map<String, Object>>) obj.get("items");

			items.forEach(item -> {
				item.entrySet().forEach(entryKind -> {
					if (entryKind.getValue().equals("DeploymentConfig")) {
						Map<String, Object> spec = (Map<String, Object>) item.get("spec");
						List<Map<String, Object>> triggers = (List<Map<String, Object>>) spec.get("triggers");
						List<Map<String, Object>> triggersToRemove = new ArrayList<>();
						triggers.forEach(trigger -> {
							if (trigger.containsKey("imageChangeParams")) {
								Map<String, Object> icp = (Map<String, Object>) trigger.get("imageChangeParams");
								((List<String>) icp.get("containerNames")).forEach(containerName -> {
									if (excludeTriggerForContainer.contains(containerName)) {
										triggersToRemove.add(trigger);
									}
								});
							}
						});
						// remove the matched triggers after foreach
						triggersToRemove.forEach(t -> triggers.remove(t));
						try {
							yaml.dump(obj, new FileWriter(deploymentFile));
						} catch (IOException ioe) {
							throw new RuntimeException(ioe);
						}
					}
				});
			});
			System.out.println(obj);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void editYamlGroovyReady() throws Exception {

		// List of container-names who will have no trigger
		List<String> excludeTriggerForContainer = asList("nginx-proxy", "mongodb");

		File deploymentFile = new File("target/classes/META-INF/fabric8/openshift.yml");

		if (deploymentFile.exists()) {

			DumperOptions options = new DumperOptions();
			options.setIndent(2);
			options.setPrettyFlow(true);
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

			Yaml yaml = new Yaml(options);

			FileInputStream inputStream = new FileInputStream(deploymentFile);

			Map<String, Object> obj = (Map<String, Object>) yaml.load(inputStream);
			List<Map<String, Object>> items = (List<Map<String, Object>>) obj.get("items");

			for (Map<String, Object> item : items) {
				for (Map.Entry<String, Object> entryKind : item.entrySet()) {
					if (entryKind.getValue().equals("DeploymentConfig")) {
						Map<String, Object> spec = (Map<String, Object>) item.get("spec");
						List<Map<String, Object>> triggers = (List<Map<String, Object>>) spec.get("triggers");
						List<Map<String, Object>> triggersToRemove = new ArrayList<>();
						for (Map<String, Object> trigger : triggers) {
							if (trigger.containsKey("imageChangeParams")) {
								Map<String, Object> icp = (Map<String, Object>) trigger.get("imageChangeParams");
								for (String containerName : (List<String>) icp.get("containerNames")) {
									if (excludeTriggerForContainer.contains(containerName)) {
										triggersToRemove.add(trigger);
									}
								}
							}
						}
						// remove the matched triggers after foreach
						for (Object t : triggersToRemove) {
							triggers.remove(t);
						}
						yaml.dump(obj, new FileWriter(deploymentFile));
					}
				}
			}
			System.out.println(obj);
		}
	}

	public void editYaml2() throws Exception {

		File deploymentFile = new File("target/classes/META-INF/fabric8/openshift.yml");

		if (deploymentFile.exists()) {
			Map<String, Object> triggerToRemove = null;

			DumperOptions options = new DumperOptions();
			options.setIndent(2);
			options.setPrettyFlow(true);
			options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

			Yaml yaml = new Yaml(options);

			FileInputStream inputStream = new FileInputStream(deploymentFile);

			Map<String, Object> obj = (Map<String, Object>) yaml.load(inputStream);
			List<Object> items = (List<Object>) obj.get("items");

			for (Object item : items) {
				Map<String, Object> i = (Map<String, Object>) item;
				for (Map.Entry<String, Object> entryKind : i.entrySet()) {
					if (entryKind.getKey().equals("kind") && entryKind.getValue().equals("DeploymentConfig")) {
						Map<String, Object> spec = (Map<String, Object>) i.get("spec");
						List<Object> triggers = (List<Object>) spec.get("triggers");
						for (Object trigger : triggers) {
							Map<String, Object> t = (Map<String, Object>) trigger;
							if (t.containsKey("imageChangeParams")) {
								Map<String, Object> ip = (Map<String, Object>) t.get("imageChangeParams");
								List<String> c = (List<String>) ip.get("containerNames");
								if (c.contains("couchdb")) {
									triggerToRemove = t;
								}
							}
						}
						if (null != triggerToRemove) {
							triggers.remove(triggerToRemove);
							yaml.dump(obj, new FileWriter(deploymentFile));
						}
					}
				}
			}
			System.out.println(obj);
		}
	}

}
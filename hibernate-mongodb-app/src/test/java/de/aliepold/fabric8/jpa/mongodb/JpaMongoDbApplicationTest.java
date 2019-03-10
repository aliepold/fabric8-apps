package de.aliepold.fabric8.jpa.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = de.aliepold.fabric8.jpa.mongodb.JpaMongoDbApplication.class)
public class JpaMongoDbApplicationTest {

	@Test  
	public void testOk() {
	
		assertTrue(true);
	}

}
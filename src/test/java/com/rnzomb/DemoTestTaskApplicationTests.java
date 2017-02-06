package com.rnzomb;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTestTaskApplicationTests {

	// Class Customer test
	@Test
	public void testSetId() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setId(4);
		assertTrue(customer.getId() == 4);
	}
	@Test
	public void testSetFirstName() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setFirstName("John");
		assertTrue(customer.getFirstName() == "John");
	}
	@Test
	public void testSetLastName() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setLastName("Test");
		assertTrue(customer.getLastName() == "Test");
	}
	@Test
	public void testSetDate() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setDate("John");
		assertTrue(customer.getDate() == "John");
	}
	@Test
	public void testSetUsername() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setUsername("Zomb");
		assertTrue(customer.getUsername() == "Zomb");
	}
	@Test
	public void testSetPassword() {
		Customer customer = new Customer(0, null, null, null, null, null);
		customer.setPassword("zxc!@#$%^&");
		assertTrue(customer.getPassword() == "zxc!@#$%^&");
	}
	
	
}
	

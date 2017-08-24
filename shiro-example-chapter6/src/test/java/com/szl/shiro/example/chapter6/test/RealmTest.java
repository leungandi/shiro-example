package com.szl.shiro.example.chapter6.test;

import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;

public class RealmTest {

	@Test
	public void testLogin() {
		String configFile = "classpath:shiro-chapter6.ini";
		BaseTest.testBase(configFile, "admin", "123");
		Subject subject = BaseTest.getSubject();
		Assert.assertTrue(subject.isAuthenticated());
	}
	
	
	
	
	
	@After
	public void after() {
		BaseTest.after();
	}

}

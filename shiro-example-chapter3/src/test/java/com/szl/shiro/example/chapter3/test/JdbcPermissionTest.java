package com.szl.shiro.example.chapter3.test;


import java.util.Arrays;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;


public class JdbcPermissionTest {

	@Test
	public void testIsPermitted() {
		String configFile = "classpath:shiro-jdbc-permission.ini";
		BaseTest.testBase(configFile,"zhang","123");
		Subject subject = BaseTest.getSubject();
		
		//断言用户是否具有“role1”角色
		Assert.assertTrue(subject.hasRole("role1"));
		
		//Shiro提供了isPermitted和isPermittedAll用于判断用户是否拥有某个权限或所有权限
		
		//断言用户是否具有“user1:create”权限
		Assert.assertTrue(subject.isPermitted("user1:create"));
		
		//断言用户是否具有“user:create”和“user:update”权限
		//Assert.assertTrue(subject.isPermittedAll("user1:create","user1:update"));
		
		BaseTest.after();
		
	}


}

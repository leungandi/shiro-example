package com.szl.shiro.example.chapter3.test;


import java.util.Arrays;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;

/**
 * 基于角色的访问控制（即隐式角色）
 * 这种方式的缺点就是如果很多地方进行了角色判断，但是有一天不需要了那么就需要修改相应代码把所有相关的地方进行删除；
 * 这就是粗粒度造成的问题。
 * @author SongZhangLiang
 * @date 2017年8月24日 上午10:56:37
 */
public class RoleTest {
	
	/**
	 * 判断用户是否拥有某个角色
	 * @author SongZhangLiang
	 * @date 2017年8月24日 上午10:35:33
	 */
	@Test
	public void testHasRole() {
		String configFile = "classpath:shiro-role.ini";
		BaseTest.testBase(configFile,"zhang","123");
		Subject subject = BaseTest.getSubject();
		
		//Shiro提供了hasRole/hasRoles用于判断用户是否拥有某个角色/某些权限
		
		//断言用户拥有"role1"的角色
		Assert.assertTrue(subject.hasRole("role1"));
		//断言用户拥有"role1","role2"的角色
		boolean[] hasRoles = subject.hasRoles(Arrays.asList("role1","role2"));
		Assert.assertTrue(hasRoles[0]);
		Assert.assertTrue(hasRoles[1]);
		BaseTest.after();
		
	}
	/**
	 * Shiro提供的checkRole/checkRoles和hasRole/hasAllRoles不同的地方是它在判断为假的情况下会抛出UnauthorizedException异常。
	 * @author SongZhangLiang
	 * @date 2017年8月24日 上午10:55:47
	 */
	@Test
	public void testCheckRole() {
		String configFile = "classpath:shiro-role.ini";
		BaseTest.testBase(configFile,"zhang","123");
		Subject subject = BaseTest.getSubject();
		try {
			subject.checkRole("role11");
		} catch (AuthorizationException e) {
			e.printStackTrace();
		}
		finally {
			BaseTest.after();
		}
	}

}

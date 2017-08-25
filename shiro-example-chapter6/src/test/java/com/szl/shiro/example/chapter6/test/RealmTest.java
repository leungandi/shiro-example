package com.szl.shiro.example.chapter6.test;

import org.apache.shiro.subject.Subject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;

public class RealmTest {
	
	/**
	 * 测试认证
	 * @author SongZhangLiang
	 * @date 2017年8月25日 下午2:39:19
	 */
	@Test
	public void testLogin() {
		String configFile = "classpath:shiro-chapter6.ini";
		BaseTest.testBase(configFile, "admin", "123");
		Subject subject = BaseTest.getSubject();
		Assert.assertTrue(subject.isAuthenticated());
	}
	
	/**
	 * 测试角色
	 * @author SongZhangLiang
	 * @date 2017年8月25日 下午2:39:56
	 */
	@Test
	public void testRole() {
		String configFile = "classpath:shiro-chapter6.ini";
		BaseTest.testBase(configFile, "admin", "123");
		Subject subject = BaseTest.getSubject();
		Assert.assertTrue(subject.hasRole("管理员"));
	}
	
	/**
	 * 测试权限
	 * @author SongZhangLiang
	 * @date 2017年8月25日 下午2:40:04
	 */
	@Test
	public void testPermissions() {
		String configFile = "classpath:shiro-chapter6.ini";
		BaseTest.testBase(configFile, "admin", "123");
		Subject subject = BaseTest.getSubject();
		Assert.assertTrue(subject.isPermitted("index"));
	}
	
	
	
	
	
	@After
	public void after() {
		BaseTest.after();
	}

}

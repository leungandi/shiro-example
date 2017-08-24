package com.szl.exalple.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

public class BaseTest {
	

	public static void after() {
		 ThreadContext.unbindSubject();
	}

	public static void testBase(String configFile,String username,String password) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		
		try {
			subject.login(usernamePasswordToken);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
	
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

}

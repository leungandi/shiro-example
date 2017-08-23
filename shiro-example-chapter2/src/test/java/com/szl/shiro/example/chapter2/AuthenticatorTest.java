package com.szl.shiro.example.chapter2;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import junit.framework.Assert;

public class AuthenticatorTest {
	
	/**
	 * AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
	 * @author SongZhangLiang
	 * @date 2017年8月23日 下午5:39:05
	 */
	@Test
	public void testAllSucess() {
		String config = "classpath:shiro-authenticator-all-success.ini";
		login(config);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipals().asList().size());
		
	}
	
	/**
	 * AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
	 * @author SongZhangLiang
	 * @date 2017年8月23日 下午5:42:30
	 */
	@Test
	public void testOneSucess() {
		String config = "classpath:shiro-authenticator-one-success.ini";
		login(config);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipals().asList().size());
		
	}
	/**
	 * FirstSuccessfulStrategy：只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
	 * @author SongZhangLiang
	 * @date 2017年8月23日 下午5:42:30
	 */
	@Test
	public void testFirstSucess() {
		String config = "classpath:shiro-authenticator-first-success.ini";
		login(config);
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.getPrincipals().asList().size());
		
	}
	
	
	public void login(String config) {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(config);
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager); 
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken upt = new UsernamePasswordToken("song", "admin");
		try {
			subject.login(upt);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
	}
	
	@After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }

}

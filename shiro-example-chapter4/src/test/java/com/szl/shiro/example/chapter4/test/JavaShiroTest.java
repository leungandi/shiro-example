package com.szl.shiro.example.chapter4.test;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * Shiro是从根对象SecurityManager进行身份验证和授权的；
 * 也就是所有操作都是自它开始的，这个对象是线程安全且真个应用只需要一个即可，因此Shiro提供了SecurityUtils让我们绑定它为全局的，方便后续操作。
 * 
 * @author SongZhangLiang
 * @date 2017年8月24日 下午2:24:28
 */

public class JavaShiroTest {
	
	/**
	 * 纯JAVA写法
	 * @author SongZhangLiang
	 * @date 2017年8月24日 下午2:24:58
	 */
	@Test
	public void testJavaShiro() {
		//SecurityManager负责真正的身份验证逻辑；它会委托给Authenticator进行身份验证；
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		//设置authenticator
		//Authenticator用于认证，协调一个或者多个Realm，从Realm指定的数据源取得数据之后进行执行具体的认证
		ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
		//设置认证策略
		authenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
		
		securityManager.setAuthenticator(authenticator);
		
		//设置authorizer 
		//Authorizer用户访问控制授权，决定用户是否拥有执行指定操作的权限。
		//Authorizer是真正的授权者，如果我们调用如isPermitted(“user:view”)，其首先会通过PermissionResolver把字符串转换成相应的Permission实例；
		//Authorizer会判断Realm的角色/权限是否和传入的匹配，如果有多个Realm，会委托给ModularRealmAuthorizer进行循环判断，如果匹配如isPermitted*/hasRole*会返回true，否则返回false表示授权失败。
		ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
		//PermissionResolver用于解析权限字符串到Permission实例
		authorizer.setPermissionResolver(new WildcardPermissionResolver());
		
		securityManager.setAuthorizer(authorizer);
		
		//设置realm
		//Realm：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；
		//可以把Realm看成DataSource，即安全数据源
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.7:3306/shiro");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		JdbcRealm realm = new JdbcRealm();
		realm.setDataSource(dataSource);
		realm.setPermissionsLookupEnabled(true);
		
		securityManager.setRealm(realm);
		
		//将SecurityManager设置到SecurityUtils 方便全局使用
		SecurityUtils.setSecurityManager(securityManager);
		
		
		//模拟用户请求token
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		Subject subject = SecurityUtils.getSubject();
		subject.login(token);
		
		//断言用户已经认证通过
		Assert.assertTrue(subject.isAuthenticated());
		
		//断言用户拥有“role1”的角色
		Assert.assertTrue(subject.hasRole("role1"));
		
		//断言用户拥有“user1:create”的权限
		Assert.assertTrue(subject.isPermitted("user1:create"));
	}


}

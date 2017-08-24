package com.szl.shiro.example.chapter3.test;


import java.util.Arrays;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import com.szl.exalple.common.BaseTest;

/*
 * 基于资源的访问控制（显示角色）
 * 也可以叫基于权限的访问控制，这种方式的一般规则是“资源标识符：操作”，即是资源级别的粒度；这种方式的好处就是如果要修改基本都是一个资源级别的修改，不会对其他模块代码产生影响，粒度小。
 * 但是实现起来可能稍微复杂点，需要维护“用户——角色，角色——权限（资源：操作）”之间的关系。  
 *
 */
public class PermissionTest {
	
	
	@Test
	public void testIsPermitted() {
		String configFile = "classpath:shiro-permission.ini";
		BaseTest.testBase(configFile,"zhang","123");
		Subject subject = BaseTest.getSubject();
		
		//Shiro提供了isPermitted和isPermittedAll用于判断用户是否拥有某个权限或所有权限
		
		//断言用户是否具有“user:create”权限
		Assert.assertTrue(subject.isPermitted("user:create"));
		
		//断言用户是否具有“user:create”和“user:update”权限
		Assert.assertTrue(subject.isPermittedAll("user:create","user:update"));
		
		BaseTest.after();
		
	}


}

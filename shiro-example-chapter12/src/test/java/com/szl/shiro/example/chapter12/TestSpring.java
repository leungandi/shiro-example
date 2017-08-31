package com.szl.shiro.example.chapter12;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szl.shiro.example.chapter12.entity.SysUsers;
import com.szl.shiro.example.chapter12.service.SysUsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-beans.xml"})
public class TestSpring {
	
	@Autowired
	private SysUsersService sysUsersService;

	@Test
	public void testSpring() {
		SysUsers user = sysUsersService.queryUser("admin");
		System.out.println(user.toString());
	}

}

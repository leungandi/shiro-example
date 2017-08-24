package com.szl.shiro.example.chapter6.test;

import java.util.Set;

import org.junit.Test;

import com.szl.shiro.example.chapter6.entity.SysPermissions;
import com.szl.shiro.example.chapter6.entity.SysRoles;
import com.szl.shiro.example.chapter6.entity.SysUsers;
import com.szl.shiro.example.chapter6.service.SysPermissionsService;
import com.szl.shiro.example.chapter6.service.SysRolesService;
import com.szl.shiro.example.chapter6.service.SysUsersService;
import com.szl.shiro.example.chapter6.service.impl.SysPermissionsServiceImpl;
import com.szl.shiro.example.chapter6.service.impl.SysRolesServiceImpl;
import com.szl.shiro.example.chapter6.service.impl.SysUsersServiceImpl;

public class ServiceTest {
	
	private SysUsersService sysUsersService = new SysUsersServiceImpl();
	private SysRolesService sysRolesService = new SysRolesServiceImpl();
	private SysPermissionsService sysPermissionsService = new SysPermissionsServiceImpl();

	
	@Test
	public void testCreateUser() {
		SysUsers user = new SysUsers();
		user.setUsername("admin");
		user.setPassword("123");
		user.setLocked(true);
		sysUsersService.createUser(user);
	}
	@Test
	public void testCreateRoles() {
		SysRoles role = new SysRoles();
		role.setRole("管理员");
		role.setDescription("这是管理员");
		role.setAvailable(true);
		sysRolesService.createRoles(role);
	}
	@Test
	public void testCreatePermissions() {
		SysPermissions permissions = new SysPermissions();
		permissions.setPermission("index");
		permissions.setDescription("首页");
		permissions.setAvailable(true);
		sysPermissionsService.createPermissions(permissions);
	}
	
	@Test
	public void testCreateUserRole() {
		sysUsersService.createUserRole(1L, 1L);
	}
	
	
	@Test
	public void testCreateRolePermissions() {
		sysRolesService.createRolePermissions(1L, 1L);
	}
	
	@Test 
	public void testQueryUserByName() {
		SysUsers user = sysUsersService.queryUser("admin");
		System.out.println(user.toString());
	}
	
	
	@Test 
	public void testQueryUserRoles() {
		Set<String> user = sysUsersService.queryRoles("admin");
		System.out.println(user.toString());
	}
	
	@Test 
	public void testQueryUserPermissions() {
		Set<String> user = sysUsersService.queryPermissions("admin");
		System.out.println(user.toString());
	}
	
	
	

}

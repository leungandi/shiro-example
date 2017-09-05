package com.szl.shiro.example.chapter14.service;

import java.util.Set;

import com.szl.shiro.example.chapter14.entity.SysUsers;

public interface SysUsersService {
	
	public int createUser(SysUsers user);
	
	public int createUserRole(Long userId,Long...roleId);
	
	public int deleteUserRole(Long userId,Long...roleId);
	
	public int deleteUser(Long id);
	
	public SysUsers queryUser(String username);
	
	public Set<String> queryRoles(String username);
	
	public Set<String> queryPermissions(String username);
	

}

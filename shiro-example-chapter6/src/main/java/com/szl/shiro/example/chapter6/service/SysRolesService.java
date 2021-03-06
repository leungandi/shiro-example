package com.szl.shiro.example.chapter6.service;

import com.szl.shiro.example.chapter6.entity.SysRoles;

public interface SysRolesService {
	
	public int createRoles(SysRoles role);
	
	public int deleteRoles(Long id);
	
	public int createRolePermissions(Long roleId,Long...permissionsId);
	
	public int deleteRolePermissions(Long roleId,Long...permissionsId);


}

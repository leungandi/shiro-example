package com.szl.shiro.example.chapter6.dao;

import com.szl.shiro.example.chapter6.entity.SysRoles;

public interface SysRolesDao {
	
	public int createRoles(SysRoles role);
	
	public int deleteRoles(Long id);
	
	public int createRolePermissions(Long roleId,Long...permissionsId);
	
	public int deleteRolePermissions(Long roleId,Long...permissionsId);


}

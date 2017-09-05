package com.szl.shiro.example.chapter14.dao;

import com.szl.shiro.example.chapter14.entity.SysRoles;

public interface SysRolesDao {
	
	public int createRoles(SysRoles role);
	
	public int deleteRoles(Long id);
	
	public int createRolePermissions(Long roleId,Long...permissionsId);
	
	public int deleteRolePermissions(Long roleId,Long...permissionsId);


}

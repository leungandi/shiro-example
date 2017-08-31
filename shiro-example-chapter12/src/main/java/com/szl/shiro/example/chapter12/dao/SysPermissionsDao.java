package com.szl.shiro.example.chapter12.dao;

import com.szl.shiro.example.chapter12.entity.SysPermissions;

public interface SysPermissionsDao {
	
	public int createPermissions(SysPermissions permissions);
	
	public int deletePermissions(Long id);

}

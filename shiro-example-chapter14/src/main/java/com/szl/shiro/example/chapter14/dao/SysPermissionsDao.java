package com.szl.shiro.example.chapter14.dao;

import com.szl.shiro.example.chapter14.entity.SysPermissions;

public interface SysPermissionsDao {
	
	public int createPermissions(SysPermissions permissions);
	
	public int deletePermissions(Long id);

}

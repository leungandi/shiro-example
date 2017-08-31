package com.szl.shiro.example.chapter12.service;

import com.szl.shiro.example.chapter12.entity.SysPermissions;

public interface SysPermissionsService {
	
	public int createPermissions(SysPermissions permissions);
	
	public int deletePermissions(Long id);

}

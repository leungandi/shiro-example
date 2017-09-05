package com.szl.shiro.example.chapter14.service;

import com.szl.shiro.example.chapter14.entity.SysPermissions;

public interface SysPermissionsService {
	
	public int createPermissions(SysPermissions permissions);
	
	public int deletePermissions(Long id);

}

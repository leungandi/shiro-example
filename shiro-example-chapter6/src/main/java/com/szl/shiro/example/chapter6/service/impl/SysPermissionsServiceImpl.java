package com.szl.shiro.example.chapter6.service.impl;

import com.szl.shiro.example.chapter6.dao.SysPermissionsDao;
import com.szl.shiro.example.chapter6.dao.impl.SysPermissionsDaoImpl;
import com.szl.shiro.example.chapter6.entity.SysPermissions;
import com.szl.shiro.example.chapter6.service.SysPermissionsService;

public class SysPermissionsServiceImpl implements SysPermissionsService {
	
	private SysPermissionsDao sysPermissionsDao = new SysPermissionsDaoImpl();

	@Override
	public int createPermissions(SysPermissions permissions) {
		return sysPermissionsDao.createPermissions(permissions);
	}

	@Override
	public int deletePermissions(Long id) {
		return sysPermissionsDao.deletePermissions(id);
	}

}

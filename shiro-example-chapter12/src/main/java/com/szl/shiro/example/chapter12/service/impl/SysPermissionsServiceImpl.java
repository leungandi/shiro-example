package com.szl.shiro.example.chapter12.service.impl;

import com.szl.shiro.example.chapter12.dao.SysPermissionsDao;
import com.szl.shiro.example.chapter12.entity.SysPermissions;
import com.szl.shiro.example.chapter12.service.SysPermissionsService;

public class SysPermissionsServiceImpl implements SysPermissionsService {
	
	private SysPermissionsDao sysPermissionsDao;

	public void setSysPermissionsDao(SysPermissionsDao sysPermissionsDao) {
		this.sysPermissionsDao = sysPermissionsDao;
	}

	@Override
	public int createPermissions(SysPermissions permissions) {
		return sysPermissionsDao.createPermissions(permissions);
	}

	@Override
	public int deletePermissions(Long id) {
		return sysPermissionsDao.deletePermissions(id);
	}

}

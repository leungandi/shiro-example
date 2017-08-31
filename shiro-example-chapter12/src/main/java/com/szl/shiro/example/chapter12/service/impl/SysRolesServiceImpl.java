package com.szl.shiro.example.chapter12.service.impl;

import com.szl.shiro.example.chapter12.dao.SysRolesDao;
import com.szl.shiro.example.chapter12.dao.impl.SysRolesDaoImpl;
import com.szl.shiro.example.chapter12.entity.SysRoles;
import com.szl.shiro.example.chapter12.service.SysRolesService;

public class SysRolesServiceImpl implements SysRolesService {
	
	private SysRolesDao sysRolesDao;

	public void setSysRolesDao(SysRolesDao sysRolesDao) {
		this.sysRolesDao = sysRolesDao;
	}

	@Override
	public int createRoles(SysRoles role) {
		return sysRolesDao.createRoles(role);
	}

	@Override
	public int deleteRoles(Long id) {
		return sysRolesDao.deleteRoles(id);
	}

	@Override
	public int createRolePermissions(Long roleId, Long... permissionsId) {
		return sysRolesDao.createRolePermissions(roleId, permissionsId);
	}

	@Override
	public int deleteRolePermissions(Long roleId, Long... permissionsId) {
		return sysRolesDao.deleteRolePermissions(roleId, permissionsId);
	}

}

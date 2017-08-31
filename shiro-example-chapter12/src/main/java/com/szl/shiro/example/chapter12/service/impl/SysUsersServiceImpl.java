package com.szl.shiro.example.chapter12.service.impl;

import java.util.Set;

import com.szl.shiro.example.chapter12.dao.SysUsersDao;
import com.szl.shiro.example.chapter12.entity.SysUsers;
import com.szl.shiro.example.chapter12.service.SysUsersService;
import com.szl.shiro.example.chapter12.util.PasswordHelper;

public class SysUsersServiceImpl implements SysUsersService {
	
	private SysUsersDao sysUsersDao;
	
	
	public void setsysUsersDao(SysUsersDao sysUsersDao) {
		this.sysUsersDao = sysUsersDao;
	}

	public void setPasswordHelper(PasswordHelper passwordHelper) {
		this.passwordHelper = passwordHelper;
	}

	private PasswordHelper passwordHelper;

	@Override
	public int createUser(SysUsers user) {
		passwordHelper.encryptPassword(user);
		return sysUsersDao.createUser(user);
	}

	@Override
	public int createUserRole(Long userId, Long... roleId) {
		return sysUsersDao.createUserRole(userId, roleId);
	}

	@Override
	public int deleteUserRole(Long userId, Long... roleId) {
		return sysUsersDao.deleteUserRole(userId, roleId);
	}

	@Override
	public int deleteUser(Long id) {
		return sysUsersDao.deleteUser(id);
	}

	@Override
	public SysUsers queryUser(String username) {
		return sysUsersDao.queryUser(username);
	}

	@Override
	public Set<String> queryRoles(String username) {
		return sysUsersDao.queryRoles(username);
	}

	@Override
	public Set<String> queryPermissions(String username) {
		return sysUsersDao.queryPermissions(username);
	}

}

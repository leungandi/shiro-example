package com.szl.shiro.example.chapter6.service.impl;

import java.util.Set;

import com.szl.shiro.example.chapter6.dao.SysUsersDao;
import com.szl.shiro.example.chapter6.dao.impl.SysUsersDaoImpl;
import com.szl.shiro.example.chapter6.entity.SysUsers;
import com.szl.shiro.example.chapter6.service.SysUsersService;
import com.szl.shiro.example.chapter6.util.PasswordHelper;

public class SysUsersServiceImpl implements SysUsersService {
	
	private SysUsersDao usersDao = new SysUsersDaoImpl();
	private PasswordHelper passwordHelper = new PasswordHelper();

	@Override
	public int createUser(SysUsers user) {
		passwordHelper.encryptPassword(user);
		return usersDao.createUser(user);
	}

	@Override
	public int createUserRole(Long userId, Long... roleId) {
		return usersDao.createUserRole(userId, roleId);
	}

	@Override
	public int deleteUserRole(Long userId, Long... roleId) {
		return usersDao.deleteUserRole(userId, roleId);
	}

	@Override
	public int deleteUser(Long id) {
		return usersDao.deleteUser(id);
	}

	@Override
	public SysUsers queryUser(String username) {
		return usersDao.queryUser(username);
	}

	@Override
	public Set<String> queryRoles(String username) {
		return usersDao.queryRoles(username);
	}

	@Override
	public Set<String> queryPermissions(String username) {
		return usersDao.queryPermissions(username);
	}

}

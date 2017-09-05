package com.szl.shiro.example.chapter14.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.szl.shiro.example.chapter14.dao.SysUsersDao;
import com.szl.shiro.example.chapter14.entity.SysUsers;

public class SysUsersDaoImpl extends JdbcDaoSupport implements SysUsersDao {


	@Override
	public int createUser(SysUsers user) {
		final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?,?)";
		return getJdbcTemplate().update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getSalt());
				ps.setBoolean(4, user.getLocked());
				return ps;
			}
		});
	}

	@Override
	public int createUserRole(Long userId, Long... roleId) {
		if (null == roleId || roleId.length == 0) {
			return 0;
		}
		String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";
		for (Long rid : roleId) {
			getJdbcTemplate().update(sql, userId,rid); 
		}
		return 1;
	}
	
	@Override
	public int deleteUserRole(Long userId, Long... roleId) {
		if (null == roleId || roleId.length == 0) {
			return 0;
		}
		String sql = "delete from sys_users_roles where id=? and user_id=?";
		for (Long rid : roleId) {
			getJdbcTemplate().update(sql,rid,userId); 
		}
		return 1; 
	}

	@Override
	public int deleteUser(Long id) {
		String sql = "delete from sys_users where id=?";
        return getJdbcTemplate().update(sql, id);
	}


	@Override
	public SysUsers queryUser(String username) {
		String sql = "select id, username, password, salt, locked from sys_users where username=?";
		List<SysUsers> query = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<SysUsers>(SysUsers.class), username);
		if (null == query || query.isEmpty()) {
			return null;
		}
		return query.get(0);
	
	}

	@Override
	public Set<String> queryRoles(String username) {
		String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";
        return new HashSet<String>(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	@Override
	public Set<String> queryPermissions(String username) {
		  String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id=ur.user_id and r.id=ur.role_id and r.id=rp.role_id and p.id=rp.permission_id";
	        return new HashSet<String>(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	

}

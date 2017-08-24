package com.szl.shiro.example.chapter6.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.szl.shiro.example.chapter6.dao.SysRolesDao;
import com.szl.shiro.example.chapter6.entity.SysRoles;
import com.szl.shiro.example.chapter6.util.JdbcTemplateUtil;

public class SysRolesDaoImpl implements SysRolesDao {

	private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getJdbcTemplate();

	@Override
	public int createRoles(SysRoles role) {
		final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";
		return jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, role.getRole());
				ps.setString(2, role.getDescription());
				ps.setBoolean(3, role.getAvailable());
				return ps;
			}
		});
	}

	@Override
	public int deleteRoles(Long id) {
		// 删除[用户-角色]表的关联
		String sql = "delete from sys_users_roles where role_id=?";
		jdbcTemplate.update(sql, id);

		// 删除角色
		sql = "delete from sys_roles where id=?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int createRolePermissions(Long roleId, Long... permissionsIds) {
		if (permissionsIds == null || permissionsIds.length == 0) {
			return 0;
		}
		String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";
		for (Long permissionId : permissionsIds) {
			jdbcTemplate.update(sql, roleId, permissionId);
		}
		return 1;
	}

	@Override
	public int deleteRolePermissions(Long roleId, Long... permissionsIds) {
		if (permissionsIds == null || permissionsIds.length == 0) {
			return 0;
		}
		String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";
		for (Long permissionId : permissionsIds) {
			jdbcTemplate.update(sql, roleId, permissionId);
		}
		return 1;
	}

}

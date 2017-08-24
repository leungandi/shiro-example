package com.szl.shiro.example.chapter6.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.szl.shiro.example.chapter6.dao.SysPermissionsDao;
import com.szl.shiro.example.chapter6.entity.SysPermissions;
import com.szl.shiro.example.chapter6.util.JdbcTemplateUtil;

public class SysPermissionsDaoImpl implements SysPermissionsDao {
	
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.getJdbcTemplate();


	@Override
	public int createPermissions(SysPermissions permissions) {
		final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";
		return jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, permissions.getPermission());
				ps.setString(2, permissions.getDescription());
				ps.setBoolean(3, permissions.getAvailable());
				return ps;
			}
		});
	}

	@Override
	public int deletePermissions(Long id) {
		//删除[权限-角色]表的关联
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, id);
        //删除权限表
        sql = "delete from sys_permissions where id=?";
        return jdbcTemplate.update(sql, id);
	}

}

package com.szl.shiro.example.chapter6.util;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcTemplateUtil {
	
	private JdbcTemplateUtil() {};
	
	private static JdbcTemplate jdbcTemplate;
	
	
	private static DataSource dataSource() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/shiro");
        ds.setUsername("root");
        ds.setPassword("123456");
        return ds;
	}
	
	
	public static JdbcTemplate getJdbcTemplate() {
		if (null == jdbcTemplate)
			jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}

}

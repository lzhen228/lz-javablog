/**  
 * Project Name		batch  
 * File Name		BaseDao.java  
 * Package Name		cn.secure.utils
 * Date				2018年5月25日下午2:49:41  
 * Copyright (c) 2018, cert@cnic.cn All Rights Reserved.  
 */  
package cn.secure.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * @Description	基础Dao类 增删改查
 * @ClassName	BaseDao
 * @author		ygc
 * @date		2018年5月25日 下午2:50:57
 * @version		V1.0
 * @since		JDK 1.8
 */
public abstract class BaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

/*	@Autowired
	private JdbcTemplate jdbcTemplate1;*/

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

/*	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate1;*/

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplateRavor;
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

/*	public JdbcTemplate getJdbcTemplate1() {
		return jdbcTemplate1;
	}*/

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}

/*	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate1() {
		return namedParameterJdbcTemplate1;
	}*/

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

/*	public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
		this.jdbcTemplate1 = jdbcTemplate1;
	}*/

	public void setNamedParameterJdbcTemplate(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

/*	public void setNamedParameterJdbcTemplate1(
			NamedParameterJdbcTemplate namedParameterJdbcTemplate1) {
		this.namedParameterJdbcTemplate1 = namedParameterJdbcTemplate1;
	}*/

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplateRavor() {
		return namedParameterJdbcTemplateRavor;
	}

}

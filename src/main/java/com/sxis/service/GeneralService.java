package com.sxis.service;

import java.sql.SQLException;

public interface GeneralService  {
	/**
	 * 插入语句
	 * 不进行sql语法校验
	 * @param insertSql
	 * @return
	 * @throws SQLException
	 */
	public long executeInsertSql(String insertSql) throws SQLException;
	/**
	 * 删除语句
	 * 不进行sql语法校验
	 * @param insertSql
	 * @return
	 * @throws SQLException
	 */
	public long executeDeleteSql(String deleteSql) throws SQLException;
	/**
	 * 更新语句
	 * 不进行sql语法校验
	 * @param insertSql
	 * @return
	 * @throws SQLException
	 */
	public long executeUpdateSql(String updateSql) throws SQLException;
	
}

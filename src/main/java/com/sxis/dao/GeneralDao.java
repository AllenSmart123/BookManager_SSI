package com.sxis.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;
/**
 * 执行已经装配好的增、删、改sql语句
 * @author Allen
 *
 */
public interface GeneralDao {
	/**
	 * 插入语句
	 * 不进行sql语法校验
	 * @param insertSql
	 * @return
	 * @throws SQLException
	 */
	public long executeInsertSql(@Param("insertSql") String insertSql) throws SQLException;
	
	/**
	 * 删除语句
	 * 不进行sql语法校验
	 * @param deleteSql
	 * @return
	 * @throws SQLException
	 */
	public long executeDeleteSql(@Param("deleteSql") String deleteSql) throws SQLException;
	
	/**
	 * 更新语句
	 * 不进行sql语法校验
	 * @param updateSql
	 * @return
	 * @throws SQLException
	 */
	public long executeUpdateSql(@Param("updateSql") String updateSql) throws SQLException;
}

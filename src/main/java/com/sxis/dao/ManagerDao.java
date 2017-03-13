package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.ManagerBean;




public interface ManagerDao {
	
	public List<ManagerBean> checkLogin(ManagerBean manager);
	public void addManagerInfo(@Param("name")String name,@Param("password")String password,@Param("grade")int grade);
    public void deleteManagerInfo(@Param("name")String name);
}

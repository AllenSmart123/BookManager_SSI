package com.sxis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.ManagerBean;

public interface ManagerService {

	public List<ManagerBean> checkLogin(ManagerBean manager);
	public void addManagerInfo(String name,String password,int grade);
	public void deleteManagerInfo(String[] name);
}

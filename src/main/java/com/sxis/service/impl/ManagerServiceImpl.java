package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.ManagerDao;
import com.sxis.model.ManagerBean;
import com.sxis.service.ManagerService;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerDao managerDao;
	
	@Override
	public List<ManagerBean> checkLogin(ManagerBean manager) {
		return managerDao.checkLogin(manager);
	}

	@Override
	public void addManagerInfo(String name, String password, int grade) {
		managerDao.addManagerInfo(name, password, grade);
	}

	@Override
	public void deleteManagerInfo(String[] name) {
		for (int i = 0; i < name.length; i++) {
			managerDao.deleteManagerInfo(name[i]);
		}
	}

}

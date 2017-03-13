package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.BorrowDao;
import com.sxis.dao.GiveBackDao;
import com.sxis.model.BorrowBean;
import com.sxis.model.GiveBackBean;
import com.sxis.service.BorrowService;
import com.sxis.service.GiveBackService;

@Service("giveBackService")
public class GiveBackServiceImpl implements GiveBackService {
	
	@Autowired
	private GiveBackDao giveBackDao;
	
	@Override
	public int getTotalGiveBackCount(){
		return giveBackDao.getTotalGiveBackCount();
	}

	@Override
	public List<GiveBackBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize) {
		return giveBackDao.getListByPage(selectName,selectValue,currentPage, pageSize);
	}

	@Override
	public int addGiveBackInfo(String readerId, String bookId, String backTime) {
		return giveBackDao.addGiveBackInfo(readerId, bookId, backTime);
	}
}

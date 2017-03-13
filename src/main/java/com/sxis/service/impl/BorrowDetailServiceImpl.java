package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.BorrowDao;
import com.sxis.dao.BorrowDetailDao;
import com.sxis.model.BorrowBean;
import com.sxis.model.BorrowDetailBean;
import com.sxis.service.BorrowDetailService;
import com.sxis.service.BorrowService;

@Service("borrowDetailService")
public class BorrowDetailServiceImpl implements BorrowDetailService {
	
	@Autowired
	private BorrowDetailDao borrowDetailDao;
	
	@Override
	public int getTotalBorrowDetailCount(){
		return borrowDetailDao.getTotalBorrowDetailCount();
	}

	@Override
	public List<BorrowDetailBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize) {
		return borrowDetailDao.getListByPage(selectName,selectValue,currentPage, pageSize);
	}

	@Override
	public int updateIfBackState(String readerId,String bookId) {
		return borrowDetailDao.updateIfBackState(readerId,bookId);
	}

	@Override
	public List<BorrowDetailBean> isBackById(String bookId, String readerId) {
		return borrowDetailDao.isBackById(bookId, readerId);
	}

	@Override
	public List<BorrowDetailBean> getBorrowInfoByIfback() {
		return borrowDetailDao.getBorrowInfoByIfback();
	}
}

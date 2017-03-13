package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.BorrowDao;
import com.sxis.model.BorrowBean;
import com.sxis.service.BorrowService;

@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {
	
	@Autowired
	private BorrowDao borrowDao;
	
	@Override
	public int getTotalBorrowCount(){
		return borrowDao.getTotalBorrowCount();
	}

	@Override
	public List<BorrowBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize) {
		return borrowDao.getListByPage(selectName,selectValue,currentPage, pageSize);
	}

	@Override
	public int getBookHouseSumById(String bookId) {
		return borrowDao.getBookHouseSumById(bookId);
	}

	@Override
	public List<BorrowBean> getBorrowBeanByCondition(String bookId,
			String readerId) {
		return borrowDao.getBorrowBeanByCondition(bookId, readerId);
	}

	@Override
	public int addBorrowInfo(String bookId, String readerId, String borrowTime,
			String backTime, int ifback) {
		return borrowDao.addBorrowInfo(bookId, readerId, borrowTime, backTime, ifback);
	}


}

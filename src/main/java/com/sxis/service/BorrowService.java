package com.sxis.service;

import java.util.List;

import com.sxis.model.BorrowBean;

public interface BorrowService {
	public List<BorrowBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize);
	public int getTotalBorrowCount();
	public int getBookHouseSumById(String bookId);
	public int addBorrowInfo(String bookId,String readerId,String borrowTime,String backTime,int ifback);
	public List<BorrowBean> getBorrowBeanByCondition(String bookId,String readerId);
}

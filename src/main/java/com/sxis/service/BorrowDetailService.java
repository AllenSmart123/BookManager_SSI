package com.sxis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BorrowBean;
import com.sxis.model.BorrowDetailBean;

public interface BorrowDetailService {
	public List<BorrowDetailBean> getBorrowInfoByIfback();
	public List<BorrowDetailBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize);
	public int getTotalBorrowDetailCount();
	public int updateIfBackState(String readerId,String bookId);
	public List<BorrowDetailBean> isBackById(String bookId,String readerId);
}

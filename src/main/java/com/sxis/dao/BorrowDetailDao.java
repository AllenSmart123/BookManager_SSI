package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BorrowDetailBean;



public interface BorrowDetailDao {
	
	public List<BorrowDetailBean> getBorrowInfoByIfback();
	public List<BorrowDetailBean> getListByPage(@Param("selectName")String selectName, @Param("selectValue")String selectValue,@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);
	public int getTotalBorrowDetailCount();
	public int updateIfBackState(@Param("readerId")String readerId,@Param("bookId")String bookId);
	public List<BorrowDetailBean> isBackById(@Param("bookId")String bookId,@Param("readerId")String readerId);
}

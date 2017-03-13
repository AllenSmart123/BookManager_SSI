package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BorrowBean;



public interface BorrowDao {
	
	public List<BorrowBean> getListByPage(@Param("selectName")String selectName, @Param("selectValue")String selectValue,@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);
	public int getTotalBorrowCount();
	public int getBookHouseSumById(@Param("bookId")String bookId);
	public int addBorrowInfo(@Param("bookId")String bookId,@Param("readerId")String readerId,@Param("borrowTime")String borrowTime,@Param("backTime")String backTime,@Param("ifback")int ifback);
	public List<BorrowBean> getBorrowBeanByCondition(@Param("bookId")String bookId,@Param("readerId")String readerId);
}

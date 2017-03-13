package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BorrowBean;
import com.sxis.model.GiveBackBean;



public interface GiveBackDao {
	
	public List<GiveBackBean> getListByPage(@Param("selectName")String selectName, @Param("selectValue")String selectValue,@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);
	public int getTotalGiveBackCount();
	public int addGiveBackInfo(@Param("readerId")String readerId,@Param("bookId")String bookId,@Param("backTime")String backTime);
}

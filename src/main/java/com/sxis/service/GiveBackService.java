package com.sxis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BorrowBean;
import com.sxis.model.GiveBackBean;

public interface GiveBackService {
	public List<GiveBackBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize);
	public int getTotalGiveBackCount();
	public int addGiveBackInfo(String readerId,String bookId,String backTime);
}

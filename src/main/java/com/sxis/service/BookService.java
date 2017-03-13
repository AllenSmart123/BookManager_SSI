package com.sxis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BookBean;

public interface BookService {
	public List<BookBean> getAllBookInfo();
	public List<BookBean> getNewestBookInfo();
	public List<BookBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize);
	public int getTotalBookCount();
	public void saveBook(BookBean book);
	public void updateBook(BookBean book);
	public int updateBookHouseSum(String bookId,int bookHouse);
	public List<BookBean> getBookById(String bookId);
	public void deleteBookByIds(String[] ids);
}

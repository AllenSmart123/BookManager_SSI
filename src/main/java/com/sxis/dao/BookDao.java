package com.sxis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sxis.model.BookBean;



public interface BookDao {
	
	public List<BookBean> getAllBookInfo();
	public List<BookBean> getNewestBookInfo();
	public void saveBook(BookBean book);
	public void updateBook(BookBean book);
	public int updateBookHouseSum(@Param("bookId")String bookId, @Param("bookHouse")int bookHouse);
	public List<BookBean> getListByPage(@Param("selectName")String selectName, @Param("selectValue")String selectValue,@Param("currentPage")int currentPage,@Param("pageSize")int pageSize);
	public int getTotalBookCount();
	public List<BookBean> getBookById(@Param("bookId")String bookId);
	public void deleteBookById(String id);
	
}

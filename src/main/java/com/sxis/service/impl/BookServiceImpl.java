package com.sxis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sxis.dao.BookDao;
import com.sxis.model.BookBean;
import com.sxis.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<BookBean> getAllBookInfo(){
		List<BookBean> blist = bookDao.getAllBookInfo();
		if(blist != null){
			return blist;
		}
		return null;
	}
	@Override
	public int getTotalBookCount(){
		return bookDao.getTotalBookCount();
	}

	@Override
	public List<BookBean> getListByPage(String selectName,String selectValue, int currentPage, int pageSize) {
		return bookDao.getListByPage(selectName,selectValue,currentPage, pageSize);
	}
	@Override
	public void saveBook(BookBean book) {
		bookDao.saveBook(book);
	}

	@Override
	public void deleteBookByIds(String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			bookDao.deleteBookById(ids[i]);
		}
	}

	@Override
	public void updateBook(BookBean book) {
		bookDao.updateBook(book);
	}
	
	@Override
	public int updateBookHouseSum(String bookId, int bookHouse) {
		return bookDao.updateBookHouseSum(bookId, bookHouse);
	}
	@Override
	public List<BookBean> getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}
	@Override
	public List<BookBean> getNewestBookInfo() {
		return bookDao.getNewestBookInfo();
	}
}

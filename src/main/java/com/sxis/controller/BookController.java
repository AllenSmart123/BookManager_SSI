package com.sxis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sxis.model.BookBean;
import com.sxis.service.BookService;

@Controller
@Scope("prototype")
@RequestMapping("/book")
public class BookController{


	@Autowired
	private BookService bookService;
	
	@RequestMapping("/getAll.do")
	public void getAllBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    int currentPage =request.getParameter("page") == null?1:Integer.parseInt(request.getParameter("page")); 
		int pageSize    = request.getParameter("rows") == null?10:Integer.parseInt(request.getParameter("rows"));
		currentPage = (currentPage-1)*pageSize;
		
		String selectName = request.getParameter("selectName") == null?"":request.getParameter("selectName"); 
		String selectValue = request.getParameter("selectValue") == null?"":request.getParameter("selectValue");
		try {
			List<BookBean> blist = bookService.getListByPage(selectName,selectValue,currentPage, pageSize);
			int total = bookService.getTotalBookCount();
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(blist).toString()+"}";
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	@RequestMapping("/saveBook.do")
	public void saveBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String bookId=null;
		String bookName=request.getParameter("bookName") == null?"":request.getParameter("bookName");
		String barcode=request.getParameter("barcode") == null?"":request.getParameter("barcode");
		String author=request.getParameter("author")== null?"":request.getParameter("author");
		String ISBN=request.getParameter("ISBN") == null?"":request.getParameter("ISBN");
		float price=request.getParameter("price") == null?0:Float.parseFloat(request.getParameter("price"));
		int page=request.getParameter("page") == null?0:Integer.parseInt(request.getParameter("page"));
		String publishTime=request.getParameter("publishTime") == null?"":request.getParameter("publishTime");
		String bookType=request.getParameter("bookType") == null?"":request.getParameter("bookType");
		int borrowDays=request.getParameter("borrowDays") == null?0:Integer.parseInt(request.getParameter("borrowDays"));
		String ownership=request.getParameter("ownership") == null?"":request.getParameter("ownership");
		String bookcaseRow=request.getParameter("bookcaseRow") == null?"":request.getParameter("bookcaseRow");
		String bookcaseColumn=request.getParameter("bookcaseColumn") == null?"":request.getParameter("bookcaseColumn");
		String bookcaseFloor=request.getParameter("bookcaseFloor") == null?"":request.getParameter("bookcaseFloor");
		String bookHouse=request.getParameter("bookHouse") == null?"":request.getParameter("bookHouse");
		
		List<BookBean> bookList = bookService.getNewestBookInfo();
		if(bookList != null && bookList.size() > 0){
			bookId = bookList.get(0).getBookId();
			String before = bookId.substring(0, 4);
		    String after = bookId.substring(4, bookId.length());
		    after = (Integer.parseInt(after)+1)+"";
		    bookId = before+after;
		}
		
		BookBean book = new BookBean();
		book.setBookId(bookId);
		book.setBookName(bookName);
		book.setBarcode(barcode);
		book.setAuthor(author);
		book.setISBN(ISBN);
		book.setPrice(price);
		book.setPage(page);
		book.setPublishTime(publishTime);
		book.setBookType(bookType);
		book.setBorrowDays(borrowDays);
		book.setOwnership(ownership);
		book.setBookcaseColumn(bookcaseColumn+"排");
		book.setBookcaseFloor(bookcaseFloor+"层");
		book.setBookcaseRow(bookcaseRow+"行");
		book.setBookHouse(bookHouse);
		
		
		try {
			bookService.saveBook(book);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	
	@RequestMapping("/updateBook.do")
	public void updateBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String bookId=request.getParameter("bookId") == null?"":request.getParameter("bookId");
		String bookName=request.getParameter("bookName") == null?"":request.getParameter("bookName");
		String barcode=request.getParameter("barcode") == null?"":request.getParameter("barcode");
		String author=request.getParameter("author")== null?"":request.getParameter("author");
		String ISBN=request.getParameter("ISBN") == null?"":request.getParameter("ISBN");
		float price=request.getParameter("price") == null?0:Float.parseFloat(request.getParameter("price"));
		int page=request.getParameter("page") == null?0:Integer.parseInt(request.getParameter("page"));
		String publishTime=request.getParameter("publishTime") == null?"":request.getParameter("publishTime");
		String bookType=request.getParameter("bookType") == null?"":request.getParameter("bookType");
		int borrowDays=request.getParameter("borrowDays") == null?0:Integer.parseInt(request.getParameter("borrowDays"));
		String ownership=request.getParameter("ownership") == null?"":request.getParameter("ownership");
		String bookcaseRow=request.getParameter("bookcaseRow") == null?"":request.getParameter("bookcaseRow");
		String bookcaseColumn=request.getParameter("bookcaseColumn") == null?"":request.getParameter("bookcaseColumn");
		String bookcaseFloor=request.getParameter("bookcaseFloor") == null?"":request.getParameter("bookcaseFloor");
		String bookHouse=request.getParameter("bookHouse") == null?"":request.getParameter("bookHouse");
		
		BookBean book = new BookBean();
		book.setBookId(bookId);
		book.setBookName(bookName);
		book.setBarcode(barcode);
		book.setAuthor(author);
		book.setISBN(ISBN);
		book.setPrice(price);
		book.setPage(page);
		book.setPublishTime(publishTime);
		book.setBookType(bookType);
		book.setBorrowDays(borrowDays);
		book.setOwnership(ownership);
		book.setBookcaseColumn(bookcaseColumn);
		book.setBookcaseFloor(bookcaseFloor);
		book.setBookcaseRow(bookcaseRow);
		book.setBookHouse(bookHouse);
		
		
		try {
			bookService.updateBook(book);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	@RequestMapping("/deleteBook.do")
	public void deleteBookByIds(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String[] ids = request.getParameter("ids").split(",");

		try {
			bookService.deleteBookByIds(ids);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
}

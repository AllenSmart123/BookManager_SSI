package com.sxis.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sxis.model.BookBean;
import com.sxis.model.BorrowBean;
import com.sxis.model.ManagerBean;
import com.sxis.service.BookService;
import com.sxis.service.BorrowService;

@Controller
@Scope("prototype")
@RequestMapping("/borrow")
public class BorrowController{


	@Autowired
	private BorrowService borrowService;
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
			List<BorrowBean> blist = borrowService.getListByPage(selectName,selectValue,currentPage, pageSize);
			int total = borrowService.getTotalBorrowCount();
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(blist).toString()+"}";
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 
	
	@RequestMapping("/borrowBook.do")
	public void borrowBook(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
	    SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    String flag = null;
		String bookId = request.getParameter("bookId") == null?"":request.getParameter("bookId"); 
		List<BookBean> bookList = bookService.getBookById(bookId);
		ManagerBean manager = (ManagerBean) request.getSession().getAttribute("manager");
		try {
			List<BorrowBean> blist = borrowService.getBorrowBeanByCondition(bookId, manager.getName());
			int total = borrowService.getBookHouseSumById(bookId);
			if(total > 0){
				if(blist != null && blist.size() > 0){
					flag = "对不起，您已借阅了此书";
					response.getWriter().write("{\"flag\":\""+flag+"\"}");
				}else{
					flag = "借阅成功";
					//借书时间
					Date nowTime = new Date(System.currentTimeMillis());
					String borrowTime = sdFormatter.format(nowTime);
					
					int days = 0;
					if(bookList != null && bookList.size() > 0){
						days = bookList.get(0).getBorrowDays();
					}
					//还书时间,从借书算起一个月后为还书时间，2592000000 为30天的毫秒数
					Date afterTime = new Date(System.currentTimeMillis()+days*86400000L);
					String backTime = sdFormatter.format(afterTime);
					borrowService.addBorrowInfo(bookId, manager.getName(), borrowTime, backTime, 0);
					int bookHouse = borrowService.getBookHouseSumById(bookId);
					bookService.updateBookHouseSum(bookId, bookHouse-1);
					response.getWriter().write("{\"flag\":\""+flag+"\"}");
				}
				
			}else{
				flag = "对不起，没有库存了";
				response.getWriter().write("{\"flag\":\""+flag+"\"}");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

}

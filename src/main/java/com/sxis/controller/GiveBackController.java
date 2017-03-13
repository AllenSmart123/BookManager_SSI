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

import com.sxis.model.BorrowDetailBean;
import com.sxis.model.GiveBackBean;
import com.sxis.model.ManagerBean;
import com.sxis.service.BookService;
import com.sxis.service.BorrowDetailService;
import com.sxis.service.BorrowService;
import com.sxis.service.GiveBackService;

@Controller
@Scope("prototype")
@RequestMapping("/giveBack")
public class GiveBackController{


	@Autowired
	private GiveBackService giveBackService;
	@Autowired
	private BorrowDetailService borrowDetailService;
	@Autowired
	private BookService bookService;
	@Autowired
	private BorrowService borrowService;
	
	@RequestMapping("/getAll.do")
	public void getAllBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    int currentPage =request.getParameter("page") == null?1:Integer.parseInt(request.getParameter("page")); 
		int pageSize    = request.getParameter("rows") == null?10:Integer.parseInt(request.getParameter("rows"));
		currentPage = (currentPage-1)*pageSize;
		
		String selectName = request.getParameter("selectName") == null?"":request.getParameter("selectName"); 
		String selectValue = request.getParameter("selectValue") == null?"":request.getParameter("selectValue");
		try {
			List<GiveBackBean> blist = giveBackService.getListByPage(selectName,selectValue,currentPage, pageSize);
			int total = giveBackService.getTotalGiveBackCount();
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(blist).toString()+"}";
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 
	
	@RequestMapping("/giveBackBook.do")
	public void giveBackBook(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
	    SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd");
	    String flag = null;
		String bookId = request.getParameter("bookId") == null?"":request.getParameter("bookId"); 
		String readerId = request.getParameter("readerId") == null?"":request.getParameter("readerId"); 
		ManagerBean manager = (ManagerBean) request.getSession().getAttribute("manager");
		try {
			List<BorrowDetailBean> blist = borrowDetailService.isBackById(bookId, manager.getName());
			if(blist != null && blist.size() > 0){
				flag = "对不起，您已经归还了此书";
				response.getWriter().write("{\"flag\":\""+flag+"\"}");
			}else{
				if(!readerId.equals(manager.getName())){
					flag = "对不起，您没有权限！";
					response.getWriter().write("{\"flag\":\""+flag+"\"}");
				}else{
					flag = "归还成功";
					//借书时间
					Date nowTime = new Date(System.currentTimeMillis());
					String backTime = sdFormatter.format(nowTime);
					//更新归还状态
					borrowDetailService.updateIfBackState(manager.getName(),bookId);
					//插入信息到归还表中去
					giveBackService.addGiveBackInfo(manager.getName(), bookId, backTime);
					int bookHouse = borrowService.getBookHouseSumById(bookId);
					bookService.updateBookHouseSum(bookId, bookHouse+1);
					response.getWriter().write("{\"flag\":\""+flag+"\"}");
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

}

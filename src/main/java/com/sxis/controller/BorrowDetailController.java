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
import com.sxis.model.BorrowDetailBean;
import com.sxis.model.ManagerBean;
import com.sxis.service.BorrowDetailService;
import com.sxis.service.BorrowService;

@Controller
@Scope("prototype")
@RequestMapping("/borrowDetail")
public class BorrowDetailController{


	@Autowired
	private BorrowDetailService borrowDetailService;
	
	@RequestMapping("/getAll.do")
	public void getAllBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    int currentPage =request.getParameter("page") == null?1:Integer.parseInt(request.getParameter("page")); 
		int pageSize    = request.getParameter("rows") == null?10:Integer.parseInt(request.getParameter("rows"));
		currentPage = (currentPage-1)*pageSize;
		
		String selectName = request.getParameter("selectName") == null?"":request.getParameter("selectName"); 
		String selectValue = request.getParameter("selectValue") == null?"":request.getParameter("selectValue");
		try {
			List<BorrowDetailBean> blist = borrowDetailService.getListByPage(selectName,selectValue,currentPage, pageSize);
			int total = borrowDetailService.getTotalBorrowDetailCount();
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(blist).toString()+"}";
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

}

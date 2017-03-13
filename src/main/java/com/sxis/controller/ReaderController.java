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
import com.sxis.model.ReaderBean;
import com.sxis.service.BookService;
import com.sxis.service.ManagerService;
import com.sxis.service.ReaderService;

@Controller
@Scope("prototype")
@RequestMapping("/reader")
public class ReaderController{


	@Autowired
	private ReaderService readerService;
	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/getAll.do")
	public void getAllBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    int currentPage =request.getParameter("page") == null?1:Integer.parseInt(request.getParameter("page")); 
		int pageSize    = request.getParameter("rows") == null?10:Integer.parseInt(request.getParameter("rows"));
		currentPage = (currentPage-1)*pageSize;
		
		String selectName = request.getParameter("selectName") == null?"":request.getParameter("selectName"); 
		String selectValue = request.getParameter("selectValue") == null?"":request.getParameter("selectValue");
		try {
			List<ReaderBean> blist = readerService.getListByPage(selectName, selectValue, currentPage, pageSize);
			int total = readerService.getTotalReaderCount();
			String json = "{\"total\":"+total+" , \"rows\":"+JSONArray.fromObject(blist).toString()+"}";
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	@RequestMapping("/saveReader.do")
	public void saveBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String readerId=null;
		String readerName=request.getParameter("readerName") == null?"":request.getParameter("readerName");
		String sex=request.getParameter("sex") == null?"":request.getParameter("sex");
		String birthday=request.getParameter("birthday")== null?"":request.getParameter("birthday");
		String identityNum=request.getParameter("identityNum") == null?"":request.getParameter("identityNum");
		String readerType=request.getParameter("readerType") == null?"":request.getParameter("readerType");
		int canBorrowNum=request.getParameter("canBorrowNum") == null?0:Integer.parseInt(request.getParameter("canBorrowNum"));
		String phone=request.getParameter("phone") == null?"":request.getParameter("phone");
		String email=request.getParameter("email") == null?"":request.getParameter("email");
		String registerDate=request.getParameter("registerDate") == null?"":request.getParameter("registerDate");
		String remark=request.getParameter("remark") == null?"":request.getParameter("remark");
		
		List<ReaderBean> readerList = readerService.getNewestReaderInfo();
		if(readerList != null && readerList.size() > 0){
			readerId = readerList.get(0).getReaderId();
			String before = readerId.substring(0, 4);
		    String after = readerId.substring(4, readerId.length());
		    after = (Integer.parseInt(after)+1)+"";
		    readerId = before+after;
		}
		
		ReaderBean reader = new ReaderBean();
		reader.setReaderId(readerId);
		reader.setReaderName(readerName);
		reader.setSex(sex);
		reader.setBirthday(birthday);
		reader.setIdentityNum(identityNum);
		reader.setReaderType(readerType);
		reader.setCanBorrowNum(canBorrowNum);
		reader.setPhone(phone);
		reader.setEmail(email);
		reader.setRegisterDate(registerDate);
		reader.setRemark(remark);
		
		
		try {
			managerService.addManagerInfo(readerName, readerName, 2);
			readerService.saveReader(reader);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	
	@RequestMapping("/updateReader.do")
	public void updateBookInfo(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String readerId=request.getParameter("readerId") == null?"":request.getParameter("readerId");;
		String readerName=request.getParameter("readerName") == null?"":request.getParameter("readerName");
		String sex=request.getParameter("sex") == null?"":request.getParameter("sex");
		String birthday=request.getParameter("birthday")== null?"":request.getParameter("birthday");
		String identityNum=request.getParameter("identityNum") == null?"":request.getParameter("identityNum");
		String readerType=request.getParameter("readerType") == null?"":request.getParameter("readerType");
		int canBorrowNum=request.getParameter("canBorrowNum") == null?0:Integer.parseInt(request.getParameter("canBorrowNum"));
		String phone=request.getParameter("phone") == null?"":request.getParameter("phone");
		String email=request.getParameter("email") == null?"":request.getParameter("email");
		String registerDate=request.getParameter("registerDate") == null?"":request.getParameter("registerDate");
		String remark=request.getParameter("remark") == null?"":request.getParameter("remark");
		
		ReaderBean reader = new ReaderBean();
		reader.setReaderId(readerId);
		reader.setReaderName(readerName);
		reader.setSex(sex);
		reader.setBirthday(birthday);
		reader.setIdentityNum(identityNum);
		reader.setReaderType(readerType);
		reader.setCanBorrowNum(canBorrowNum);
		reader.setPhone(phone);
		reader.setEmail(email);
		reader.setRegisterDate(registerDate);
		reader.setRemark(remark);
		
		
		try {
			readerService.updateReader(reader);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
	
	@RequestMapping("/deleteReader.do")
	public void deleteBookByIds(HttpServletRequest request,HttpServletResponse response){  
	    response.setContentType("application/json;charset=utf-8");  
		
	    String[] ids = request.getParameter("ids").split(",");

		try {
			managerService.deleteManagerInfo(ids);
			readerService.deleteReaderByIds(ids);
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} 

	} 
}

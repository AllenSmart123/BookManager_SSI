package com.sxis.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.sxis.model.ManagerBean;
import com.sxis.service.ManagerService;

@Controller
@Scope("prototype")
@RequestMapping("/manager")
public class ManagerController{


	@Autowired
	private ManagerService managerService;
	
	@RequestMapping("/login.do")
	public String login(ManagerBean manager,Model model,HttpServletRequest request, HttpServletResponse response){
		List<ManagerBean> mlist = managerService.checkLogin(manager);
		if(mlist != null && mlist.size() > 0){
			request.getSession().setAttribute("manager", mlist.get(0));
			
			InetAddress addr = null;
			try {
				addr = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		    request.getSession().setAttribute("ip", addr.getHostAddress());
			
			//model.addAttribute("manager",manager);
			return "/BookManagerSystem1";
		}else{
			return "/";
		}
	}
	
	/**
	 * 
	 * @param manager
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/clear.do")
	public String login(Model model,HttpServletRequest request, HttpServletResponse response){
		ManagerBean manager = (ManagerBean) request.getSession().getAttribute("manager");
		if(manager != null){
			request.getSession().removeAttribute("manager");
			JSONObject json = new JSONObject();
			json.put("status", "ok");
			json.put("message", "登录成功");
			try {
				response.getWriter().write(json.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}

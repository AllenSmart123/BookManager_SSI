package com.sxis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sxis.model.ManagerBean;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {

		ManagerBean manager = (ManagerBean) request.getSession().getAttribute("manager");  
        System.out.println("用户为=========>"+manager);  
        if(manager!=null){  
            return true;  
        }
        //request.getRequestDispatcher("/BookManager_V1").forward(request, response);  
        return false;  
	}

}

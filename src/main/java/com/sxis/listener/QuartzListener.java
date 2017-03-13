package com.sxis.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;

import com.sxis.util.CronTriggerEmp;

public class QuartzListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("监听器初始化");
		try {
			 new CronTriggerEmp().task();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}

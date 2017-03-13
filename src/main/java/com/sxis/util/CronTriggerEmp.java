package com.sxis.util;

import java.text.ParseException;

import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 
 * 每隔5秒钟检查D盘有无test.txt，如果有则复制到C盘
 * 
 * @author JavaChaos
 * 
 */

public class CronTriggerEmp {

	public void task() throws SchedulerException {

		// 初始化一个调度工厂

		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		// 初始化一个调度器

		Scheduler scheduler = schedulerFactory.getScheduler();

		// JobDetail对象相当于你要调度得任务

		JobDetail jobDetail = new JobDetail("jobDetail-c1","jobDetailGroup-s1", QuartzJob.class);

		// CronTrigger对象相当于你的配置信息

		CronTrigger cronTrigger = new CronTrigger("cronTrigger","triggerGroup2");

		try {

			// cron表达式

			CronExpression expression = new CronExpression("0/10 * * * * ?");// 每隔10秒执行一次 
			// CronExpression expression = new CronExpression("0 19 16 * * ?");// 每天16：19触发

			 
			// 把Cron表达式传给CronTrigger

			cronTrigger.setCronExpression(expression);

			// 传入参数

			scheduler.scheduleJob(jobDetail, cronTrigger);

			// 开始进行调度

			scheduler.start();

		} catch (ParseException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		try {
			System.out.println("开始执行。。。。。。。");

			CronTriggerEmp runner = new CronTriggerEmp();

			runner.task();

		} catch (SchedulerException e) {

			e.printStackTrace();

		}

	}

}

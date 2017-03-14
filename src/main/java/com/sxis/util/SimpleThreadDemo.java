package com.sxis.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;

/**
 *  @author geal
 *  springMail发送邮件
 *  SendMail.java
 */
public class SimpleThreadDemo {

	/**
	 * 简单创建线程方法
	 * @param args
	 */
	public static void main( String[] args ) {

		Thread thread = new Thread(  ){
		   @Override
			public void run(){
			   try {
				   while( true ){
					   System.out.println("节点开始发心跳了。");
					   Thread.sleep( 1000*15*1L );
				   }
			   } catch( InterruptedException e ) {
				   e.printStackTrace();
			   }
		   }
		};
		thread.start();

	}

	public static void testThread(){
		try {
			Thread t1 = new Thread(){
				public void run()  {
					while( true ){
						System.out.println("节点开始发送心跳了");
						//1分钟发送一次
						try {
							Thread.sleep( 1000*60*1L  );
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}};
			Thread t2 = new Thread(){
				public void run()  {
					while( true ){
						System.out.println("节点开始接受心跳了");
						//1分钟发送一次
						try {
							Thread.sleep( 1000*60*1L  );
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}};

			t1.start();
			t1.join();
			t2.start();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}
	
}

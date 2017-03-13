package com.sxis.util;
import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
/**
 *  @author geal
 *  springMail发送邮件
 *  SendMail.java
 */
public class SendMail {
	public ApplicationContext ctx = null;
	public SendMail() {
		ctx = new FileSystemXmlApplicationContext("classpath:spring/applicationContext-mail.xml");
	}
	/**
	 * 主测试方法
	 * 
	 * @throws MessagingException
	 */
	public static void main(String[] args) {
		new SendMail().sendMail("AllenSmart123@163.com","javaEE");
	}
	/**
	 * 发送简单邮件
	 */
	public void sendMail(String receiveEmail,String content_bookName) {
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// 获取JavaMailSender
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo(receiveEmail);// 接受者
			mail.setFrom("kangdeyinyue2@163.com");// 发送者
			mail.setSubject("图书借阅：提前三天到期通知");// 主题
			mail.setText("springMail----请尽快归还图书:"+content_bookName);// 邮件内容
			sender.send(mail);
			System.out.println("发送完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

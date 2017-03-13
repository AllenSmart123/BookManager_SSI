package com.sxis.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.sxis.model.BookBean;
import com.sxis.model.BorrowDetailBean;
import com.sxis.model.ReaderBean;
import com.sxis.service.BookService;
import com.sxis.service.BorrowDetailService;
import com.sxis.service.ReaderService;

/**
 * 
 * 读取D盘文件将其内容写入C盘文件中
 * 
 * @author
 *  
 */

public class QuartzJob implements Job {
	
	@Autowired
	private BorrowDetailService borrowDetailService;
	@Autowired
	private BookService bookService;
	@Autowired
	private ReaderService readerService;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	 public void printSomething(){
		    //定时发邮件，提前三天通知借阅者
		    try {
				List<BorrowDetailBean> blist = borrowDetailService.getBorrowInfoByIfback();
				if(blist != null && blist.size() > 0){
					System.out.println("list大小为："+blist.size());
					int i = 0;
					for(BorrowDetailBean borrowDetailBean:blist){
						
						String bookId = borrowDetailBean.getBookId();
						String readerId = borrowDetailBean.getReaderId();
						List<BookBean> bookList = bookService.getBookById(bookId);
						List<ReaderBean> readerList = readerService.getReaderById(readerId);
						String backTime = borrowDetailBean.getBackTime();
						String nowTime = sdf.format(new Date());
						System.out.println(nowTime);
						long days = (sdf.parse(backTime).getTime()-sdf.parse(nowTime).getTime())/(24*60*60*1000);
						//距离还书日期，提前三天发邮件告诉借阅者
						if(days < 3 && readerList != null && bookList != null){
							//邮件通知
							new SendMail().sendMail(readerList.get(0).getEmail(),bookList.get(0).getBookName());
						}
						System.out.println((i++)+"="+days);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
	 }
	 
	 
	 public void execute(JobExecutionContext context) throws JobExecutionException {
			System.out.println(context.getTrigger().getName()+"....定时监控器");
	 }
}

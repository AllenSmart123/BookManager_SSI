package com.sxis.test;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Created by Allen on 2017/3/14.
 */
public class DateFormatTest {
	@Test
	public void test(){
		//格式化
		DateTime dateTime = new DateTime(  );
		System.out.println(dateTime.toString( "yyyy-MM-dd HH:mm:ss" ));
		//毫秒转时间
		System.out.println(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format( new java.util.Date(System.currentTimeMillis()) ));
	}
}

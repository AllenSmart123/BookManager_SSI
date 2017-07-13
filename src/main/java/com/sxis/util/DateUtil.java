package com.sxis.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DateUtil {
	public final static Logger logger = LoggerFactory.getLogger( DateUtil.class );
	public static final int BUFFER = 8192;

    public static void main( String[] args ) {
        try {
            java.util.Date date;
            // 首先设置"Mon Dec 28 00:00:00 CST 2008"的格式,用来将其转化为Date对象
            DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

            //将已有的时间字符串转化为Date对象
            date = df.parse("Thu May 25 00:00:00 CST 2017");// 那天是周一
            // 创建所需的格式
            df = new SimpleDateFormat("yyyy-MM-dd");
            String str = df.format(date);// 获得格式化后的日期字符串
            System.err.println(str);// 打印最终结果

            System.err.println(df.format( new Date(  ) ));// 打印最终结果







            DateTime dt = new DateTime( new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse( "Thu May 25 00:00:00 CST 2017" ) );

            System.out.println("=============================================="+ dt.toString("yyyy-MM-dd HH:mm:ss"));


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("==============================================");


       /* DateTimeFormatter format = DateTimeFormat.forPattern( "EEE MMM dd HH:mm:ss zzz yyyy");
        //时间解析
        DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);*/


       /* DateTimeFormatter dateTimeFormatter = DateTimeFormat.f .forPattern( "EEE MMM dd HH:mm:ss zzz yyyy");

        DateTime dateTime = DateTime.parse("Thu May 25 00:00:00 CST 2017", dateTimeFormatter );*/
        //System.out.println(dateTime2.toString( "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US ));

        System.out.println( new DateTime().toString( "EEE MMM dd HH:mm:ss zzz yyyy", Locale.US ) );


    }

	
}

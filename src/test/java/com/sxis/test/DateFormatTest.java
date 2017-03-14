package com.sxis.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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

	public void testAdd(){
		try {
			assertEquals(0,0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void testCollection(){
		Set<String> set1 = Sets.newHashSet("1","2","3","4","5");
		Set<String> set2 = Sets.newHashSet("1","2","3","6");
		Sets.SetView<String> result1 = Sets.difference( set1,set2 );
		Sets.SetView<String> result2 = Sets.intersection( set1,set2 );
		Sets.SetView<String> result = Sets.union( set1,set2 );
		Iterator<String> iterator = result.iterator();
		while( iterator.hasNext() ){
			System.out.println(iterator.next());
		}
		System.out.println(result1.iterator());
		System.out.println(result2);
		System.out.println(result);
	}

	@Test
	public void testStr(){
		String str = "1|2|3|4|5|6|7|8|9";
		Iterable<String> strings = Splitter.on( "|" ).split( str );
		for( String s: strings) {
			System.out.println(s);
		}

		List<String> lists = Lists.newArrayList( "1",null,"3","4","5","6","7","8","9" );
		System.out.println(lists.size());

		System.out.println( Joiner.on( "|" ).skipNulls().join( lists ));


	}
}

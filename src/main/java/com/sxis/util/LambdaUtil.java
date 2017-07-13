package com.sxis.util;

/**
 * 类名称：cpm.java<br>
 * 类描述：汉字排序方案3<br>
 * 要求将汉字以英文字母进行排序
 * 先需Comparator部署环境
 * 运用Collections 函数
 * 创建时间：2016年12月23日, 下午3:09:31
 */
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.text.Collator;
import java.util.*;
import java.util.Comparator;
public class LambdaUtil {
	public static void main(String[] args) {

		try {
			String[] newArray={"简单","写写","我的","理解","bbfer","yuy","rr","kiu","awc","张泮祺","孙荣大","万雨辰","安祉臣","宋明瑶","李博一","李正彭","吴政航","徐豪"};
			List<String> newList = Arrays.asList(newArray);

			String[] players = {"Rafael Nadal", "Novak Djokovic",
					"Stanislas Wawrinka", "David Ferrer",
					"Roger Federer", "Andy Murray",
					"Tomas Berdych", "Juan Martin Del Potro",
					"Richard Gasquet", "John Isner"};

			String[] newString = {"tt","ed","yy","uu","aa"};

			/*PinyinTool pinyinTool = new PinyinTool();
			System.out.println(pinyinTool.toPinYin( "大大方方" ));*/


			// 1.1 使用匿名内部类根据 name 排序 players

			Collections.sort( newList , new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					try {
						s1 = new PinyinTool().toPinYin( s1 );
						s2 = new PinyinTool().toPinYin( s2 );
					} catch( BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination ) {
						badHanyuPinyinOutputFormatCombination.printStackTrace();
					}
					return (s1.compareTo(s2 ));
				}
			});

			/*Arrays.sort(newArray, new Comparator<String>() {
				@Override
				public int compare(String s1, String s2) {
					try {
						s1 = new PinyinTool().toPinYin( s1 );
						s2 = new PinyinTool().toPinYin( s2 );
					} catch( BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination ) {
						badHanyuPinyinOutputFormatCombination.printStackTrace();
					}
					return (s1.compareTo(s2 ));
				}
			});*/


			/*Arrays.sort(newArray, (String s1, String s2) ->
			{
				PinyinTool pinyinTool = new PinyinTool();
				return s1.compareTo(s2);
			}
			);*/


			System.out.println(newList.toString());
		} catch( Throwable e ) {
			e.printStackTrace();
		} finally {
		}
	}
}

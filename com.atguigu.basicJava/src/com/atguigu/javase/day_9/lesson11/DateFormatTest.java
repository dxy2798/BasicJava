package com.atguigu.javase.day_9.lesson11;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
/**
 * Date() 封装了时间和日期.
 * 
 * DateFormat: 把日期对象格式化为一个字符串 & 把一个字符串转为一个 Date 对象.
 * 1. DateFormat 是一个抽象类. 
 * 		抽象类获取对象的方式:
 *      1). 创建其子类对象.
 *      2). 有的抽象类中提供了静态工厂方法来获取抽象类的实例.
 *      
 */
public class DateFormatTest {
	
	@Test
	public void testSimpleDateFormat() throws ParseException{
		
		DateFormat dateFormat = 
				new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		String dateStr = "1990-12-12 12:12:12";
		Date date2 = dateFormat.parse(dateStr);
		System.out.println(date2);
	}
	
	@Test
	public void testDateFormat() throws ParseException{
		
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, 
				DateFormat.LONG);
		Date date = new Date();
		String str = dateFormat.format(date);
		
		System.out.println(str);
		
		str = "2019年7月4日 上午09时18分42秒";
		Date date2 = dateFormat.parse(str);
		System.out.println(date2);
	}
	

	@Test
	public void testDate() {
		
		Date date = new Date();
		System.out.println(date);
	}

}

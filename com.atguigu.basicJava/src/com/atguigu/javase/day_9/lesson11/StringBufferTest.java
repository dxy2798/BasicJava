package com.atguigu.javase.day_9.lesson11;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * StringBuffer: 是可以被修改的字符序列
 * 
 * 1. append() 方法: 把字符串加入到已有的字符序列的后面
 *    注意: append() 方法的返回值还是当前的 StringBuffer 对象.
 *    可以使用方法的连缀.
 * 2. StringBuilder VS  StringBuffer
 * 	  StringBuilder 是线程不安全的,效率更高.所以更多的时候使用 StringBuilder.
 *    StringBuffer  是线程安全的,效率偏低,在多线程的情况下使用.
 *    
 *
 */
public class StringBufferTest {

	@Test
	public void testAppend(){
		StringBuilder stringBuilder = 
				new StringBuilder();
		
		stringBuilder.append("<html>")
					.append("<body>")
					.append("</body>")
					.append("</html>");
		System.out.println(stringBuilder);
	}
	
	@Test
	public void testStringBuilder() {
		StringBuffer stringBuffer = 
				new StringBuffer("abcde");
		System.out.println(stringBuffer);
		stringBuffer.replace(1, 3, "mvp");
		
		System.out.println(stringBuffer);
		
	}

}

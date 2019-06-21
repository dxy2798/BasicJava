package com.atguigu.javase.lesson7;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.junit.Test;

public class EnumerationTest {

	/**
	 * Enumeration 接口是 Iterator 迭代器的"古老版本"
	 */
	@Test
	public void testEnumeration() {
		
		Hashtable hashtable = new Hashtable();
		hashtable.put("A", "1");
		hashtable.put("B", "2");
		hashtable.put("C", "3");
		hashtable.put("D", "4");
		hashtable.put("E", "5");
		
		Enumeration enumeration = hashtable.elements();
		
		while(enumeration.hasMoreElements()){
			Object obj = enumeration.nextElement();
			System.out.println(obj);
		}
/*		Enumeration enumeration = new StringTokenizer("This is a pen.");
		
		while(enumeration.hasMoreElements()){
			Object obj = enumeration.nextElement();
			System.out.println(obj);
		}*/
	}

}

package com.atguigu.jdbc;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

public class BeanUtilsTest {
	@Test
	public void testGetProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Object object = new Student();
		//System.out.println(object);
		
		BeanUtils.setProperty(object, "idCard", "211121196509091876");
		//System.out.println(object);
		
		Object val = BeanUtils.getProperty(object, "idCard");
		System.out.println(val);
	}
	
	@Test
	public void testSetProperty() throws IllegalAccessException, InvocationTargetException {
		Object object = new Student();
		System.out.println(object);
		
		BeanUtils.setProperty(object, "idCard", "211121196509091876");
		System.out.println(object);
	}

}

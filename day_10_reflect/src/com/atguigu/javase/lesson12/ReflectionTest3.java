package com.atguigu.javase.lesson12;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectionTest3 {

	/**
	 * 获取 clazz 的 methodName 方法,该方法可能是私有方法,还可能在父类中(私有方法)
	 * @param clazz
	 * @param methodName
	 * @param paramterTypes
	 * @return
	 */
	public Method getMethod(Class clazz,String methodName,Class ...paramterTypes){
		// 2. 调用 clazz 的 getDeclaredMethod() 方法获取 2 对应的 Method 对象.
		// 注意:
		// 2.1  因为该方法可能不在当前类中,所以有可能需要去父类中获取.
		// 2.2 因为给定的条件中并没有给定参数类型的列表,需要从 args 来获取参数类型的列表.
		for(;clazz != Object.class; clazz = clazz.getSuperclass()){
			try {
				Method method = clazz.getDeclaredMethod(methodName, paramterTypes);
				return method;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return null;
	}
	
	/**
	 * 由 Object 数组得到对应的 Class 数组
	 * @param args
	 * @return
	 */
	public Class [] getParamterTypes(Object ... args){
		
		Class [] parameterTypes = new Class[args.length];
		for(int i = 0; i < args.length; i++){
			parameterTypes [i] = args[i].getClass();
		}
		return parameterTypes;
	}
	
	/**
	 * 执行 obj 对象的 method 方法, 参数值为 args
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Object invokeMethod(Object obj, Method method, Object[] args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		// 3. 因为 Method 可能是私有的,所以需要让其变为可访问的: setAccessible(true)
		method.setAccessible(true);
		// 4. 调用 Method
		// 4.1 利用 1 得到的 clazz 先创建 className 对应的类的一个对象.
		//Object obj = clazz.newInstance();
		// 4.2 再调用 Method 的 invoke() 方法执行方法.
		Object result = method.invoke(obj, args);
		return result;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	
	@Test
	public void testClassMethod() throws Exception{
		// 1.全类名
		String className = "com.atguigu.javase.lesson12.Student";
		// 2.方法名: 可能在1给的类中,也可能在父类中,可能是私有方法,也可能是共有方法.
		String methodName = "method3";
		// 3.执行 2 对应的方法时需要传入的参数列表. 
		Object [] args = {"尚硅谷",25};
		
		// 根据以上条件,执行 methodName 对应的方法,并打印返回值.
		
		// 1. 加载 className 对应的类,获取对应的 Class 对象 clazz.
		Class clazz = Class.forName(className);
		Class [] parameterTypes = getParamterTypes(args);
		
		Method method = getMethod(clazz, methodName, parameterTypes);
		Object result = invokeMethod(clazz.newInstance(), method, args);
		
		System.out.println(result.toString());
	}

	
	
	/**
	 * 
	 * @param obj: 某个类的一个对象.
	 * @param methodName: 类的一个方法的方法名.该方法也可能是私有方法.
	 * 					  还可能是该方法在父类中定义的(私有)方法
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用方法后的返回值.
	 */
	
	@Test
	public void testInvoke3(){
		Object obj = new Student();
		invoke2(obj, "method1", 10);
		//Student 类的 method1() 方法被调用,打印 "private void method1"
		
		Object result = invoke3(obj,"method2");
		//Student 类的父类的 method2() 方法被调用,返回值为"private String method2"
		System.out.println(result);
	}
	
	public Object invoke3(Object obj,String methodName,
			Object ...args){
		
		//1. 获取 Method 对象.
		Class [] parameterTypes = new Class [args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = getMethod(obj.getClass(), methodName, parameterTypes);
			method.setAccessible(true);
			//2. 执行 Method 方法.
			//3. 返回方法的返回值.
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public Object invoke2(Object obj,String methodName,
			Object ...args){
		
		//1. 获取 Method 对象.
		Class [] parameterTypes = new Class [args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
			//2. 执行 Method 方法.
			//3. 返回方法的返回值.
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	

	
	@Test
	public void testGetMethod() throws Exception{
		
		Class clazz = Class.forName("com.atguigu.javase.lesson12.Student");
		
		Method method = getMethod(clazz,"method1",Integer.class);
		System.out.println(method);
		
		method = getMethod(clazz, "method2");
		System.out.println(method);
	}
	
	@Test
	public void testInvoke2(){
		Object obj = new Student();
		invoke2(obj, "method1", 10);
		//Student 类的 method1() 方法被调用,打印 "private void method1"
		
		Object result = invoke2(obj,"method2");
		//Student 类的父类的 method2() 方法被调用,返回值为"private String method2"
		//System.out.println(result);
	}
	
	/**
	 * 若通过 Method 的 invoke() 调用方法,而访问权限不足,则可以先使该方法
	 * 变为可访问的:
	 * method.setAccessible(true);
	 * @throws Exception
	 */
	@Test
	public void testInvokePrivateMethod() throws Exception{
		Object obj = new Student();
		
		Class clazz = obj.getClass();
		Method method = clazz.getDeclaredMethod("method1",Integer.class);
		System.out.println(method);
		
		//若需要通过反射执行私有方法.
		method.setAccessible(true);
		method.invoke(obj, 10);
	}

}

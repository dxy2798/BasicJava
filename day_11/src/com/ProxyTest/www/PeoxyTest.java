package com.ProxyTest.www;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class PeoxyTest {
	
	@Test
	public void testPersonService(){
		//Service target = new ServiceImpl();
		//Service proxy = new ServiceTest(target).testProxy();
		
		ServiceTest serviceTest = new ServiceTest();
		Service proxy = serviceTest.testProxy();
		
		
		System.out.println(ServiceImpl.getPerson());
		
		proxy.addNew(new Person(1005,"CCC"));
		System.out.println(ServiceImpl.getPerson());
		
		proxy.delete(1001);
		System.out.println(ServiceImpl.getPerson());
		
		proxy.update(new Person(1002, "MMM"));
		System.out.println(ServiceImpl.getPerson());
	}
	

	/**
	 * 测试如何实现动态代理
	 */
	@Test
	public void testProxy1(){
		/**
		 * ClassLoader: 由动态代理产生的对象由哪个类加载器来加载.
		 * 通常情况下和被代理对象使用一样的类加载器.
		 * Class<?>[]: 由动态代理产生的对象必须实现的接口的 Class 数组.
		 * InvocationHandler: 当具体调用代理对象的方法时,将产生什么行为.
		 */
		
		ArithmeticCalculator arithmeticCalculator = 
				new ArithmeticCalculatorImpl();
		ArithmeticCalculator proxy = 
				(ArithmeticCalculator) Proxy.newProxyInstance(arithmeticCalculator.getClass().getClassLoader(), 
				new Class[]{ArithmeticCalculator.class}, 
				new InvocationHandler() {
					/**
					 * proxy:
					 * method: 正在被调用的方法.
					 * args: 调用方法时传入的参数.
					 */
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) 
							throws Throwable {

						System.out.println("The method " 
								+ method.getName() 
								+ " begins with " 
								+ Arrays.asList(args));
						
						// 调用被代理类的目标方法.
						Object result = method.invoke(arithmeticCalculator, args);
						System.out.println("The method "
								+ method.getName() 
								+ " end with " + result);
						return result;
					}
				});
		
		proxy.mul(1, 2);
		
		int result = proxy.add(2, 5);
		
	}
	
	
	
	@Test
	public void testCalculator() {
		
		ArithmeticCalculator arithmeticCalculator = 
				new ArithmeticCalculatorImpl();
		
		arithmeticCalculator.add(1, 2);
		arithmeticCalculator.sub(1, 2);
	}

}

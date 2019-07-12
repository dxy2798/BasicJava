package com.atguigu.javase.lesson12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void testProxy(){
		/**
		 * ClassLoader: 由动态代理产生的对象由哪个类加载器来加载. 
		 * 通常情况下和被代理对象使用一样的类加载器.
		 * Class<?>[]: 由动态代理产生的对象必须要实现的接口的Class 数组. 
		 * InvocationHandler: 当具体调用代理对象的方法时,将产生什么行为.
		 * 
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
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								System.out.println("method: " + method);
								return 0;
							}
						});
		
		proxy.mul(1, 2);
		int result = proxy.add(2, 5);
		System.out.println(result);
	}
	
	
	
	@Test
	public void testCalculator() {

		ArithmeticCalculator arithmeticCalculator = 
				new ArithmeticCalculatorImpl();
		arithmeticCalculator.add(2, 3);
		
		
		
	}

}

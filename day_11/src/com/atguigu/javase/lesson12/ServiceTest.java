package com.atguigu.javase.lesson12;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class ServiceTest {
	/**
	 * 定义一个 Service 接口
	 * 定义如下方法:
	 *  addNew(Person person);
	 *  delete(Integer id);
	 *  update(Person person);
	 *  
	 *  并提供具体的实现类.
	 *  
	 *  使用动态代理实现事务操作.
	 *  1. 在具体调用每个 Service 方法前,都打印: 开始事务.
	 *  2. 方法正常结束,都打印: 提交事务.
	 *  3. 若在调用目标方法出异常情况下: 事务回滚.
	 */
	@Test
	public void testProxy() {
		
		Service service = new ServiceImpl();
		
		Service proxy = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(),
				new Class[]{Service.class}, 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("开始事务");
						Object result = null;
						try{
							result = method.invoke(service, args);
							System.out.println(method.getName() + "|" + Arrays.asList(args));
							System.out.println("提交事务");
							return result;
							
						}catch(Exception e){
							System.out.println("事务回滚");
							e.printStackTrace();
						}
						
						return null;
					}
				});
		
/*		proxy.addNew(new Person(1, "小张"));
		proxy.delete(1);
		proxy.update(new Person(2,"小王"));*/
		
	}

}

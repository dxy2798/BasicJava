package com.atguigu.javase.lesson7.review;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class ReviewTest {

	//1. interface 接口
	@Test
	public void testInterface(){
		Servlet servlet = new GenericServlet();
		
		servlet.init();
		
	}
	//2. 内部类
	public void testInnerClass(){
		//匿名内部类对象.
		InvocationHandler handler = new InvocationHandler() {
			//接口中要实现的方法
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		
		Object object = Proxy.newProxyInstance(null, null, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		
	}
	//3. 异常处理: 能举出常见的 4 个异常; 处理异常的两种方式(try...catch...finally、throws)\
	//   人工在方法内部使用 throw 关键字抛出异常; 自定义异常.
	@Test
	public void testException() throws ClassNotFoundException{
		//算数异常、空指针异常、角标越界异常、文件不存在异常.
		Servlet servlet = new GenericServlet();
		GenericServlet gs = (GenericServlet) servlet;
		// 编译能通过是因为 OuterClass 和 Servlet 都是 Object 的子类
		// obj 是一个 Object 对象,它有可能是一个 Servlet 对象.
		// 但是 obj 是一个 OutClass ,所以强转势必会发生一个类型转换异常 ClassCastException.
		//Object obj = new OuterClass();
		//Servlet servlet2 = (Servlet) obj;
		
		//方法1
		/*try {
			Class.forName("com.atguigu.javase.lesson7.review.OuterClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
		//方法2
		//Class.forName("com.atguigu.javase.lesson7.review.OuterClass");
		
		login("MM");
	}
	
	
	public void login(String name){
		Set names = new HashSet();
		
		names.add("AA");
		names.add("BB");
		names.add("CC");
		names.add("DD");
		
		if(names.contains(name)){
			System.out.println("登录成功！");
		}else{
			throw new UserNotExistException("用户名不存在!");
		}
		
	}
	
	
	
	//4. Collection 集合
	//4.1 Collection 是一个接口
	
	
	@Test
	public void testCollection(){
		
		Collection collection = null;
		
		
	}
	
	//5. Set: HashSet、LinkedHashSet
	//Set: 里边不允许存放相同的元素
	//两个元素相同的标准是: equals() 方法比较后返回 true
	//HashSet: hashCode() 比较结果和 equals 比较结果必须一致.不能保证元素的顺序.
	//LinkedHashSet: HashSet 的子类,可以保证元素的顺序.
	@Test
	public void testSet(){
		
		LinkedHashSet set = new LinkedHashSet();
		
		set.add("AA");
		set.add("BB");
		set.add("CC");
		set.add("DD");
		set.add("EE");
		
		Iterator it = set.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
		
	}
	
	
	
	
}

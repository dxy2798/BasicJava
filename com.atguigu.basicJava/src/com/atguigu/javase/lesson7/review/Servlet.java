package com.atguigu.javase.lesson7.review;

/**
 * 1. 接口使用 interface 来声明.
 * 2. 接口是一个特殊的抽象类: 里边只有方法的声明和常量的定义.
 * 2.1 方法默认的修饰符为: public abstract
 * 2.2 常量默认的修饰符为: public static final
 * 
 * 3. 接口和抽象类一样,不能被直接实例化.
 * 4. 实现接口使用 implements.
 * 5. 接口和实现类之间也存在着多态的关系.
 * 
 * 面向接口编程: JDBC 时详细说明.
 */
public interface Servlet {

	String GET = "GET";
	
	void init();
	
	void destroy();
	
	void service();
	
	
	
}

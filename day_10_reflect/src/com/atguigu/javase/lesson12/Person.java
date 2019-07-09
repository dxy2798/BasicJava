package com.atguigu.javase.lesson12;

public class Person {
   
	String name;
	private int age;
	
	private void test(){};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setName(String name,Integer age) {
		System.out.println("name: " + name);
		System.out.println("age: " + age);
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
		System.out.println("Person 的有参数的构造器.");
	}
	
	/**
	 * 留给反射使用的.
	 */
	public Person() {
		System.out.println("Person 的无参数的构造器.");
	}
	
	private String method2(){
		return "private String method2";
	}
	
	private Object method3(String name,Integer age){
		
		Person person = new Person(name, age);
		
		return person;
	}
	
}

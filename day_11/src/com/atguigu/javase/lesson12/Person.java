package com.atguigu.javase.lesson12;

public class Person {
	
	private int id;
	private String name;
	
	
	public Person(int id,String name) {
		this.name = name;
		this.id = id;
	}
	
	public Person() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + "]";
	}
	
	
}

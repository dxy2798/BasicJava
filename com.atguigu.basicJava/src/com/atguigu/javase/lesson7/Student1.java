package com.atguigu.javase.lesson7;

import static org.hamcrest.CoreMatchers.instanceOf;

public class Student1{

	private String name;
	private int score;
	//private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Student1(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}
	
	public Student1() {

	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", score=" + score + "]";
	}

}

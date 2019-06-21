package com.atguigu.javase.lesson7.review;

public class UserNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UserNotExistException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserNotExistException(String msg) {
		super(msg);
	}	
	
}

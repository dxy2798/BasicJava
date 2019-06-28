package com.atguigu.javase.lesson7.Bank;

public class Account {

	protected double balance;

	public Account(double balance) {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public boolean withdraw(double m){
		if(balance < m){
			System.out.println("余额不足！");
			return false;
		}else{
			balance = balance - m;
			return true;
		}	
	}
	/**
	 * 存款方法
	 * @param m
	 */
	public boolean deposit(double m){
		balance = balance + m;
		return true;
	}
}

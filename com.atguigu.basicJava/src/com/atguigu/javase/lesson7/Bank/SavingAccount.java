package com.atguigu.javase.lesson7.Bank;

public class SavingAccount extends Account{

	private double interestRate;

	protected SavingAccount(double balance,double interestRate) {
		super(balance);
		this.interestRate = interestRate;
	}


	
}

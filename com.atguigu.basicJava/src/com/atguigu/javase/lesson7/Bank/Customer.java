package com.atguigu.javase.lesson7.Bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer {

	private String firstName;
	private String lastName;
	private List<Account> accounts;
	private int numberOfAccounts;
	
	
	public Customer (String f,String l){
		this.firstName = f;
		this.lastName = l;
		accounts = new ArrayList<Account>();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Account getAccount(int index) {
		return accounts.get(index);
	}
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public int getNumberOfAccounts(){
		//return numberOfAccounts;
		
		return accounts.size();
	}
	
	public Iterator<Account> getAccouts(){
		return accounts.iterator();
	}
}

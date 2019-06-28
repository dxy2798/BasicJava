package com.atguigu.javase.lesson7.Bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bank {

//	private Customer[] customers;
//	private int numberOfCustomers;
	private List<Customer> customers;
//	public Bank(){
//		customers = new Customer[5];
//	}
	private Bank(){
		customers = new ArrayList<Customer>();
	}
	
	
	private static Bank instance = new Bank();
	
	public static Bank getInstance() {
		return instance;
	}
	
	
	public void addCustomer(String f,String l){
		Customer customer = new Customer(f, l);
//		customers[numberOfCustomers] = customer;
//		numberOfCustomers++;
		customers.add(new Customer(f, l));

	}
	
	public Customer getCustomer(int index){
		//return customers[index];
		return customers.get(index);
		
	}

	public int getNumberOfCustomers() {
		//return numberOfCustomers;
		return customers.size();
	}

//	public void setNumberOfCustomers(int numberOfCustomers) {
//		this.numberOfCustomers = numberOfCustomers;
//	}
	
	public Iterator<Customer> getCustomers(){
		return customers.iterator();
	}
}

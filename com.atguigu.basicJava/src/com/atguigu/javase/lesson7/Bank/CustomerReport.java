package com.atguigu.javase.lesson7.Bank;

import java.util.Iterator;

public class CustomerReport {
	
	Customer customer = null;
	Bank bank = Bank.getInstance();
	public void generateReport(){
		System.out.println("\t\t\tCUSTOMERS REPORT");
		System.out.println("\t\t\t=============");
		
		Iterator<Customer> customerIterator = bank.getCustomers();
		
		while(customerIterator.hasNext()){
//		for(int cust_idx = 0;cust_idx < bank.getNumberOfCustomers();cust_idx++){
			//customer = bank.getCustomer(cust_idx);
			customer = customerIterator.next();
			System.out.println();
			System.out.println("Customer: "
					+ customer.getLastName() + ","
					+ customer.getFirstName());
			
			Iterator<Account> accoutIterator = customer.getAccouts();
			
			//for(int acct_idx = 0; acct_idx < customer.getNumberOfAccounts(); acct_idx++){
			while(accoutIterator.hasNext()){	
				//Account account = customer.getAccount(acct_idx);
				Account account = accoutIterator.next();
				String account_type = "";
				
				if(account instanceof SavingAccount){
					account_type = "Saving Account";
				}else if(account instanceof CheckingAccount){
					account_type = "Checking Account";
				}
				System.out.println(account_type + ": current balance is ¥" + account.getBalance());
			}
		}
	}
	
	
}

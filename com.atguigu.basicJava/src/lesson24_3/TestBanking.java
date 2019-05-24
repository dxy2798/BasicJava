package lesson24_3;

import java.text.NumberFormat;

public class TestBanking {

	public static void main(String[] args) {
		
		NumberFormat curr_Format = NumberFormat.getCurrencyInstance();
		
		Bank bank = new Bank();
		Customer customer;
		bank.addCustomer("Joan", "Simms");
		customer = bank.getCustomer(0);
		customer.addAccount(new SavingAccount(500.00, 0.05));
		customer.addAccount(new CheckingAccount(200.00,400.00));
		
		bank.addCustomer("Owen", "Bryant");
		customer = bank.getCustomer(1);
		customer.addAccount(new CheckingAccount(200.00));
		
		bank.addCustomer("Tim", "Soley");		
		customer = bank.getCustomer(2);
		customer.addAccount(new SavingAccount(1500.00, 0.05));
		customer.addAccount(new CheckingAccount(200.00));

		bank.addCustomer("Maria", "Soley");		
		customer = bank.getCustomer(3);
		customer.addAccount(bank.getCustomer(2).getAccount(1));
		customer.addAccount(new CheckingAccount(150.00,0.05));		
		
		System.out.println("\t\t\tCUSTOMERS REPORT");
		System.out.println("\t\t\t=============");
		
		for(int cust_idx = 0;cust_idx < bank.getNumberOfCustomers();cust_idx++){
			customer = bank.getCustomer(cust_idx);
			System.out.println();
			System.out.println("Customer: "
					+ customer.getLastName() + ","
					+ customer.getFirstName());
			for(int acct_idx = 0; acct_idx < customer.getNumberOfAccounts(); acct_idx++){
				Account account = customer.getAccount(acct_idx);
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

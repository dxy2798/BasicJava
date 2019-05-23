package banking;

public class testBanking {

	public static void main(String[] args) {

/*		Account act = null;
		
		act = new Account(500.00);
		
		System.out.println(act.getBalance());
		act.withdraw(150.00);
		System.out.println(act.getBalance());
		act.deposit(22.5);
		System.out.println(act.getBalance());
		act.withdraw(47.62);
		System.out.println(act.getBalance());*/
		
		
		Customer customer = null;
		
		customer = new Customer("Jane", "Smith");
		
		Account act = new Account(500.00);
		
		customer.setAccount(act);
		
		System.out.println(customer.getAccount().withdraw(150));
		
		System.out.println(customer.getAccount().deposit(22.50));
		
		System.out.println(customer.getAccount().withdraw(47.62));
		
		System.out.println(customer.getFirstName() + " " + customer.getLastName() + " has a balance of " + customer.getAccount().getBalance());
		
	}

}

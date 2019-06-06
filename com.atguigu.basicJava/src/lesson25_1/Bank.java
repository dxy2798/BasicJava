package lesson25_1;

public class Bank extends Account {

	
	public static void main(String[] args) {
		Account account1 = new Bank();
		Account account2 = new Bank();
		Account account3 = new Bank();
		System.out.println(account1.account_number);
		System.out.println(account2.account_number);
		System.out.println(account3.account_number);
	}
	
	
	
}

package lesson24_3;

public class Customer {

	private String firstName;
	private String lastName;
	private Account[] accounts;
	private int numberOfAccounts;
	
	
	public Customer (String f,String l){
		this.firstName = f;
		this.lastName = l;
		accounts = new Account[2];
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Account getAccount(int index) {
		return accounts[index];
	}
	public void addAccount(Account account) {
		accounts[numberOfAccounts++] = account;
	}
	
	public int getNumberOfAccounts(){
		return numberOfAccounts;
	}
	
}

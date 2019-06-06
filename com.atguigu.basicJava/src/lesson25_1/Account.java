package lesson25_1;

public class Account {
	protected String account_number ;
	protected String password;
	protected double balance;
	protected double interestRate;
	protected double minBalance;
	
	protected int value = 0;
	
	public Account() {
		value++;
		this.account_number = new String("0000" + value);
	}
	
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	public Account(String password, double balance, double interestRate, double minBalance) {
		super();
		this.password = password;
		this.balance = balance;
		this.interestRate = interestRate;
		this.minBalance = minBalance;
		value++;
		this.account_number = new String("0000" + value);
		
	}
	
	
	
}

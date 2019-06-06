package lesson25_2;

public class Account {
	
	private int id;
	private int balance;
	private String password;
	
	private static double interestRate;
	private static int minBalance;
	
	private static int value = 1001;
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", balance=" + balance + ", password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static double getInterestRate() {
		return interestRate;
	}

	public static void setInterestRate(double interestRate) {
		Account.interestRate = interestRate;
	}

	public static int getMinBalance() {
		return minBalance;
	}

	public static void setMinBalance(int minBalance) {
		Account.minBalance = minBalance;
	}

	public Account(int balance, String password) {
		super();
		this.id = value;
		value ++;
		this.balance = balance;
		this.password = password;
	}

	public Account() {
		super();
		this.id = value;
		value ++;
	}

	
}

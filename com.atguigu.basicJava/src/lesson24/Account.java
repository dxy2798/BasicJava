package lesson24;

public class Account {

	private int id;
	private double balance;
	private double annualInterestRate;
	
	
	public Account() {
		super();
	}
	
	public Account(int id, double balance, double annualInterestRate) {
		super();
		this.id = id;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
	/**
	 * 返回月利率
	 * @return
	 */
	public double getMonthlyInterest(){
		return annualInterestRate / 12;
	}
	/**
	 * 取款方法
	 * @param m
	 */
	public void withdraw(double m){
		if(balance < m){
			System.out.println("余额不足！");
			return;
		}else{
			balance = balance - m;
		}	
	}
	/**
	 * 存款方法
	 * @param m
	 */
	public void deposit(double m){
		balance = balance + m;
	}
}

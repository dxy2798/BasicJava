package BankExceptionEX;

public class Account {

	protected double balance;

	public Account(double balance) {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void withdraw(double m){
		if(balance < m){
			
			throw new OverdraftException("资金不足" , (m - balance));
			
		}else{
			balance = balance - m;
			
		}	
	}
	/**
	 * 存款方法
	 * @param m
	 */
	public boolean deposit(double m){
		balance = balance + m;
		return true;
	}
}

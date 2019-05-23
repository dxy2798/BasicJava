package banking;

public class Account {
	
	//当前余额
	private double balance;
	
	public Account(double balance){
		this.balance = balance;
	}
	
	public double getBalance() {
		return balance;
	}
	public boolean deposit(double d){
		balance = balance + d;
		return true;
		
	}
	
	public boolean withdraw(double d){
		if(d <= balance){
			balance = balance - d;
			return true;
		}else{
			return false;
		}
	}
	
}

package losson24_2;

public class CheckingAccount extends Account{

	private double overdraftProtection;

	public CheckingAccount(double balance,double overdraftProtection) {
		super(balance);
		this.overdraftProtection = overdraftProtection;
	}

	public CheckingAccount(double balance) {
		super(balance);
	}

	
	public double getOverdraftProtection() {
		return overdraftProtection;
	}

	public void setOverdraftProtection(double overdraftProtection) {
		this.overdraftProtection = overdraftProtection;
	}

	public boolean withdraw(double amt){
		
		if(balance >= amt){
			balance -= amt;
		}else{
			if(overdraftProtection >= (amt - balance)){
				overdraftProtection = overdraftProtection - (amt - balance);
				balance = 0;
			}else{
				System.out.println("余额不足！账户余额为: " + balance + ",透支保护余额还有: " + overdraftProtection);
				return false;
			}
		}
		
		return true;
	}
	
}

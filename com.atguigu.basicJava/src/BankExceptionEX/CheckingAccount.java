package BankExceptionEX;

public class CheckingAccount extends Account{

	private double overdraftProtection = -1;

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

	public void withdraw(double amt){
		//余额足够
		if(balance >= amt){
			balance -= amt;
		}else{
			//没有透支保护
			if(overdraftProtection == -1){
				throw new OverdraftException("no overdraft protection   ", amt - balance);
			}
			//透支保护足够
			if(overdraftProtection >= (amt - balance)){
				overdraftProtection -= (amt - balance);
				balance = 0;
			}
			//余额不足,且透支保护也不足
			else{
				throw new OverdraftException("Insufficient funds for overdraft protection  ", amt - balance);
			}
			
		}

	}
	
}

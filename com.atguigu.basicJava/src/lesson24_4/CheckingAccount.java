package lesson24_4;

public class CheckingAccount extends Account{

	private SavingAccount protectedBy;
	
	public CheckingAccount(double init_balance) {
		super(init_balance);
	}

	
	public CheckingAccount(double balance, SavingAccount protectedBy) {
		super(balance);
		this.protectedBy = protectedBy;
	}

	public boolean withdraw(double amt){
		
		if(balance >= amt){
			balance -= amt;
		}else{
			if(protectedBy != null && protectedBy.getBalance() >= (amt - balance)){
				protectedBy.withdraw(amt - balance);
				balance = 0;
			}else{
				//System.out.println("余额不足！账户余额为: " + balance + ",透支保护余额还有: " + protectedBy.getBalance());
				System.out.println("余额不足！");
				return false;
			}
		}
		
		return true;
	}
	
}

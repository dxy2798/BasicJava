package lesson24;

public class CheckAccount extends Account{

	private double overdraft;


	public CheckAccount(int id, double balance, double annualInterestRate,double overdraft) {
		super(id, balance, annualInterestRate);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}
	
	public void withdraw(double m){
		if(m <= super.getBalance()){
			super.withdraw(m);
			return;
		}else if(m > super.getBalance()){
			if(overdraft + super.getBalance() >= m){
				double balance = super.getBalance();
				super.setBalance(0);
				overdraft = overdraft + balance - m;
			}else{
				System.out.println("超出可透支限额！");
			}
		}
	}
	
}

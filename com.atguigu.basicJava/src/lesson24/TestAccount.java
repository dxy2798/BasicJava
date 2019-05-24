package lesson24;

public class TestAccount {

	public static void main(String[] args) {

		Account act = new Account();
		act.setId(1122);
		act.setBalance(20000);
		act.setAnnualInterestRate(0.045);
		
		act.withdraw(30000);
		System.out.println("您的余额为: " + act.getBalance());
		
		act.withdraw(2500);		
		act.deposit(3000);
		System.out.println("您的余额为: " + act.getBalance());
		System.out.println("月利率为: " + act.getMonthlyInterest());
	}

}

package lesson24;

public class TestCheckAccount {

	public static void main(String[] args) {
		
		CheckAccount checkAccount = new CheckAccount(1122, 20000, 0.045,5000);
		
		checkAccount.withdraw(5000);
		System.out.println("你的账户余额为: " + checkAccount.getBalance());
		System.out.println("你的可透支额为: " + checkAccount.getOverdraft());
		
		System.out.println("====");
		
		checkAccount.withdraw(18000);
		System.out.println("你的账户余额为: " + checkAccount.getBalance());
		System.out.println("你的可透支额为: " + checkAccount.getOverdraft());	

		System.out.println("====");
		
		checkAccount.withdraw(3000);
		System.out.println("你的账户余额为: " + checkAccount.getBalance());
		System.out.println("你的可透支额为: " + checkAccount.getOverdraft());
		
	}

}

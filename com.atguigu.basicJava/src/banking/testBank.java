package banking;

public class testBank {

	public static void main(String[] args) {
		
		Bank bk = new Bank();

		bk.addCustomer("Jane", "Smith");
		bk.addCustomer("Owen", "Bryant");
		bk.addCustomer("Tim", "Soley");
		bk.addCustomer("Maria", "Soley");
	
		for(int i = 0;i < bk.getNumberOfCustomers(); i++){
			System.out.println("Customer[" + (i + 1) + "] is " + bk.getCustomer(i).getFirstName() + bk.getCustomer(i).getLastName());
			
		}
		
	}

}

package losson24_2;

public class Bank {

	private Customer[] customers;
	private int numberOfCustomers;
	
	public Bank(){
		customers = new Customer[5];
	}
	
	public void addCustomer(String f,String l){
		Customer customer = new Customer(f, l);
		customers[numberOfCustomers] = customer;
		numberOfCustomers++;
	}
	
	public Customer getCustomer(int index){
		return customers[index];
	}

	public int getNumberOfCustomers() {
		return numberOfCustomers;
	}

	public void setNumberOfCustomers(int numberOfCustomers) {
		this.numberOfCustomers = numberOfCustomers;
	}
}

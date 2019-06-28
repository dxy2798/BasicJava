package lesson28_1;

public class Bank {

	private Customer[] customers;
	private int numberOfCustomers;
	//单子模式
	//1. 私有化构造器(目的是外部无法创建实例,只能从内部创建)
	private Bank(){
		customers = new Customer[5];
	}
	
	//2. 使用 static 修饰,
	private static Bank instance = new Bank();
	//提供公有的get方法访问,即不能在类的外部修改该属性
	public static Bank getInstance() {
		return instance;
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

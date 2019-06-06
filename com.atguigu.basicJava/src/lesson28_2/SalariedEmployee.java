package lesson28_2;

public class SalariedEmployee extends Employee {

	private int weeklySalary;
	
	@Override
	int earnings() {
		return weeklySalary;
	}

	@Override
	public String toString() {
		return "SalariedEmployee " + super.toString();
	}

	public SalariedEmployee(String name, int number, MyDate birthday, int weeklySalary) {
		super(name, number, birthday);
		this.weeklySalary = weeklySalary;
	}

	
	

}

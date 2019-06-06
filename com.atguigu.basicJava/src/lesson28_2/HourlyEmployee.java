package lesson28_2;

public class HourlyEmployee extends Employee {

	private int wage;
	private int hour;
	
	@Override
	int earnings() {
		return wage * hour;
	}

	@Override
	public String toString() {
		return "SalariedEmployee" + super.toString();
	}

	public HourlyEmployee(String name, int number, MyDate birthday, int wage, int hour) {
		super(name, number, birthday);
		this.wage = wage;
		this.hour = hour;
	}

}

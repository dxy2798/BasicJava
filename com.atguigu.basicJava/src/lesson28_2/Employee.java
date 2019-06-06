package lesson28_2;

import java.util.Date;

public abstract class Employee {

	protected String name;
	protected int number;
	protected MyDate birthday;
	
	abstract int earnings();

	public Employee(String name, int number, MyDate birthday) {
		this.name = name;
		this.number = number;
		this.birthday = birthday;
	}

	public Employee() {

	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", number=" + number + ", birthday=" + birthday + "]";
	}
	
	public MyDate getBirthday() {
		return birthday;
	}
}

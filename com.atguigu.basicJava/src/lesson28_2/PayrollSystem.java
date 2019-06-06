package lesson28_2;

import java.util.Calendar;
import java.util.Date;

public class PayrollSystem {

	
	
		public static void main(String[] args) {
	
			Employee[] employees = new Employee[3];
			
			Calendar calendar = Calendar.getInstance();
			
			employees[0] = new SalariedEmployee("Tom", 1001, 
					new MyDate(12, 12, 1990), 3000);
			employees[1] = new SalariedEmployee("Jerry", 1002, 
					new MyDate(4, 6, 1990), 3000);
			employees[2] = new HourlyEmployee("Mike", 1003, 
					new MyDate(5, 6, 1990), 200,8);
			
			for(Employee employee : employees){
				System.out.println(employee);
				System.out.println("工资: " + employee.earnings());
				
				if(employee.getBirthday().getMonth() == calendar.get(Calendar.MONTH) + 1){
					System.out.println("生日补贴: 100");
				}
				System.out.println();
			}
			
		}
	
	
}

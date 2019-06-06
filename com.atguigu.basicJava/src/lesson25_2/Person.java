package lesson25_2;

public class Person {
	public static int total;
	
	static{
		total = 100;
		System.out.println("in static block");
		total ++;
	}
}

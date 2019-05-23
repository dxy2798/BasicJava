package lesson14;

public class testPerson {

	public static void main(String[] args) {
		
		Person p1 = new Person("Tom", 10);
		
		Person p2 = new Person("Jerry", 11, "atguigu");
		
		Person p3 = new Person("Joan", 12, "female");
		
		System.out.println(p1.getInfo());
		System.out.println(p2.getInfo());
		System.out.println(p3.getInfo());
	}

}

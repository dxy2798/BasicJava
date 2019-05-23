package lesson18;

public class TestPerson {

	public static void main(String[] args) {

		Person p1 = new Person();
		
		p1.walk();
		
//		Man man = new Man();
//		man.walk();
//		man.work();
		
		Person p2 = new Man();
		p2.walk();
		
		Man man = (Man)p2;
		man.work();
		
		System.out.println(p2);
		System.out.println(man);
		
		Person p3 = new Woman();
		p3.walk();
		
	}

}

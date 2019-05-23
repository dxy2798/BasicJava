package lesson3;

public class testPerson {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.age = 11;
		person.name = "Tom";
		
		Person p1 = new Person();
		p1.age = 20;
		p1.name = "Jerry";		
		

		//person.work();
		
		//System.out.println("My name is " + person.name + ", and my age is " + person.age);
		
		//person.sayHello(person.name);
		
		System.out.println(p1.getAge());
		
	}

}

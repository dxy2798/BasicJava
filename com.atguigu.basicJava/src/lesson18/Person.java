package lesson18;

public class Person {

	String name;
	int age;
	
	public String getInfo(){
		return "name: " + name + ",age: " 
				+ age;
	}
	
	void walk(){
		System.out.println("走路...");
	}

	public Person(){
		
	}
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

}

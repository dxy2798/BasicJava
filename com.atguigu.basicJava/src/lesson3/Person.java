package lesson3;

public class Person {

	String name;
	int age;
	
	void work(){
		System.out.println("Work ... ");
	}
	
	void sayHello(String personName){
		System.out.println("Hello: " + personName + "!");
	}
	
	Class<Person> getPerson (String PersonName){
		
		return Person.class;
	}
	
	int getAge(String personName){
		getPerson(personName).getPackage();
		return age;
	}


	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

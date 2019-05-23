package lesson23;

public class TestInstance {
	
	public void method1(Person e){
		if(e instanceof Graduate){
			System.out.println("a graduated student");
			System.out.println("a student");
			System.out.println("a person");
		}else if(e instanceof Student){
			System.out.println("a student");
			System.out.println("a person");
		}else if(e instanceof Person){
			System.out.println("a person");
		}
	}

	public static void main(String[] args) {
		
		TestInstance t = new TestInstance();
		
		t.method1(new Student());
		System.out.println("");
		t.method1(new Person());
		System.out.println("");
		t.method1(new Graduate());
	}

}

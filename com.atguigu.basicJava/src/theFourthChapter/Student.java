package theFourthChapter;

public class Student extends Person{

	String school;
	
	public String getInfo(){
//		return "name: " + name 
//				+ ", age: " + age + ", school: " + school;
		
		return super.getInfo() + ", school: " + school;
	}
	
//	public void test(){
//		System.out.println(super.getInfo());
//	}

}

package theFourthChapter;

public class TestExtends {
	

	public static void main(String[] args) {
		
		Student student = new Student();
		
		student.name = "Tom";
		student.age = 25;
		student.school = "尚硅谷";
		
		System.out.println(student.getInfo());

	}

}

package lesson3;

public class testStudent {

	public static void main(String[] args) {
		
/*		Student stu = new Student();
		stu.setAge(18);
		stu.setMajor("Java");
		stu.setStudentName("Jerry");*/
		Student stu = new Student("Tom");
		stu.setAge(20);
		stu.setMajor("Phtyon");
		
		System.out.println(stu.getStudentName() + "|" + stu.getAge() + "|" + stu.getMajor());
		
		
		
	}

}

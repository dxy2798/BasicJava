package lesson3;

public class Student {
	private int age;
	private String major;
	private String studentName;
	
	public Student(){
		
	}
	public Student(String name){
		studentName = name;
	}	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	
	
	
}

package day_8;

public class Student extends Person {

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Student(String name, int age, int score) {
		super(name, age);
		this.score = score;
	}
	
	public Student() {
		
	}
	
	
}

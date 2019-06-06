package lesson25_1;

public class Chinese {
	
	private static String country;
	private String name;
	private int age;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public Chinese(String country, String name, int age) {
		super();
		this.country = country;
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Chinese [country=" + country + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
}

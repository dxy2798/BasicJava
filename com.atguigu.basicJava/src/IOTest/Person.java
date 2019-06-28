package IOTest;

import java.io.Serializable;

public class Person implements Serializable{


	// 类的版本号: 用于对象的序列化.具体用于读取对象时比对硬盘上对象的
	//          版本和程序中对象的版本是否一致，若不一致读取失败，并抛异常.
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private Address address;
	
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public Person(String name, int age,Address address) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", address=" + address + "]";
	}

	
}

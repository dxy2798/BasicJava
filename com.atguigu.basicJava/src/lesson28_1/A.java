package lesson28_1;

public class A {

	static{
		System.out.println("父类的静态代码块...");
	}
	
	{
		System.out.println("父类的非静态代码块...");
	}
	
	public A() {
		System.out.println("父类的构造器...");
	}

}
class B extends A{
	
	static{
		System.out.println("子类的静态代码块...");
	}
	
	{
		System.out.println("子类的非静态代码块...");
	}
	
	B() {
		System.out.println("子类的构造器...");
	}
	
	
}
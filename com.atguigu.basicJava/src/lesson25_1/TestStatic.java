package lesson25_1;

/**
 *  1. static 是一个关键字: 用于修饰类的成员(属性,方法,内部类).
 *  2. 若访问权限允许的情况下, static 修饰的成员可以通过类名.方法名
 *     来访问. 即可以在不创建对象的情况下进行访问. 当然也可以通过对象.方法名
 *     的方式访问
 *  3. static 修饰的成员称为 类成员, 为类的所有实例所共享.     	 
 *	4. 在 static 方法内部只能访问累的 static 属性,不能访问类的非 static 属性.
 *	       反之, 非 static 方法可以访问 static 成员.
 *	5. 因为不需要实例就可以访问 static 方法,因此 static 方法内部不能有 this 和 super.
 *	6. 静态初始化: static 修饰的代码块,当类加载时执行,且只被执行一次. 作用是对静态属性初始化.
 *	7. 所谓类的单态设计模式, 就是采取一定的方法保证在整个的软件系统中, 对某个类只能存在一个对象实例.
 *
 * 
 */
public class TestStatic {

	static String name;
	int age;
	
	//非静态代码块: 类似不带参的构造器
	{
		age = 10;
		System.out.println("非静态代码块...");
	}
	
	//静态代码块
	static
	{
		name = "";
		System.out.println("静态代码块...");
	}
	public TestStatic(){
		System.out.println("构造器...");
	}
	
	static void test(){
		name = "";
		//age = 12;
	}
	
	void method(){
		name = "bcd";
		System.out.println(name);
	}
	
	public static void main(String[] args) {

		TestStatic.name = "";
		TestStatic.test();
		
		TestStatic ts = new TestStatic();
		ts.age = 12;
		ts.name = "ABC";
		ts.method();
		
		System.out.println(ts.name);
		
		TestStatic ts2 = new TestStatic();
		System.out.println(ts2.name);
		
		ts2.method();
		
	}

}

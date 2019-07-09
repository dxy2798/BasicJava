package com.atguigu.javase.lesson12;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.event.FocusEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;
import org.junit.internal.runners.TestMethod;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.portable.InvokeHandler;

public class ReflectionTest {


	
	/**
	 * 获取当前类的父类
	 * 直接调用 Class 的 getSuperclass() 方法.
	 * @throws Exception 
	 */
	
	@Test
	public void testGetSuperClass() throws Exception{
		String className ="com.atguigu.javase.lesson12.Student";
		Class clazz = Class.forName(className);
		Class superClass = clazz.getSuperclass();
		System.out.println(superClass.getName());
	}
	

	/**
	 * 
	 * @param className: 某个类的全类名.
	 * @param methodName: 类的一个方法的方法名.
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用方法后的返回值.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public Object invoke(String className,String methodName,Object ... args){
		
		Object obj = null;
		
		try {
			obj = Class.forName(className).newInstance();
			return invoke(obj, methodName, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return null;
	}
	/**
	 * 
	 * @param obj: 方法执行的那个对象.
	 * @param methodName: 类的一个方法的方法名.该方法也可能是私有方法.
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用方法后的返回值.
	 */
	public Object invoke(Object obj,String methodName,Object ... args){
		//1. 获取 Method 对象.
		Class [] parameterTypes = new Class [args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
			System.out.println(parameterTypes[i]);
		}
		
		try {
			Method method = obj.getClass().getMethod(methodName, parameterTypes);
			//2. 执行 Method 方法.
			//3. 返回方法的返回值.
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	@Test
	public void testInvoke(){
		Object obj = new Person();
		invoke(obj, "setName", "尚硅谷",1);
		invoke("com.atguigu.javase.lesson12.Person","setName","atguigu", 2);
	
		Object result =
				invoke("java.text.SimpleDateFormat", "format", new Date());
		System.out.println(result);
	}
	
	
	
	/**
	 * Class 是对一个类的描述.
	 * 类的属性: Field
	 * 类的方法: Method
	 * 类的构造器: Constrctor
	 * 
	 * Method: 对应类中的方法.
	 * 1. 获取 Method:
	 * 1.1  获取类的方法的数组:Method [] methods2 = clazz.getDeclaredMethods();
	 * 1.2  获取类的指定的方法:getDeclaredMethod(String name,
     *                           Class<?>... parameterTypes)
	 *	   name: 方法名.
	 * 	   parameterTypes: 方法的参数类型(使用 Class 来描述)的列表.
	 *     
	 *     Method method = 
	 *     		clazz.getDeclaredMethod("setName", String.class);
	 * 
	 *     method = 
	 *          clazz.getDeclaredMethod("setName", String.class,Integer.class);
	 * 
	 * 1.3 通过 method 对象执行方法:
	 * public Object invoke(Object obj,Object... args)
     *
     *  obj: 执行哪个对象的方法?
     *  // method 对应的原方法为:
     *  public void setName(String name,Integer age) 
     *  method.invoke(obj, "尚硅谷",12);
     *  
     *  
     *  
     *  
	 *  
	 * 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	
	@Test
	public void TestMethod() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class clazz = Class.forName("com.atguigu.javase.lesson12.Person");
		
		//1. 得到 clazz 对应的类中有哪些方法.不能获取 private 私有方法
		
		Method [] methods = clazz.getMethods();
		for(Method method : methods){
			System.out.println("^" + method.getName());
		}
		
		//2. 获取所有的方法,包括 private 私有方法,且只获取当前类声明的方法.
		Method [] methods2 = clazz.getDeclaredMethods();
		for(Method method : methods2){
			System.out.println("~" + method.getName());
		}
		
		//3. 获取指定的方法.
		Method method = clazz.getDeclaredMethod("setName", String.class);
				System.out.println("+++" + method);
				
		method = clazz.getDeclaredMethod("test");		
		System.out.println(method);
		
		method = clazz.getDeclaredMethod("setName", String.class,Integer.class);
		System.out.println(method);
		
		//4. 执行方法!
		Object obj = clazz.newInstance();
		method.invoke(obj, "尚硅谷", 12);
		
		
	}
	
	
	@Test
	public void testClassLoader() throws ClassNotFoundException, FileNotFoundException{
		//1. 获取一个系统的类加载器.
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		System.out.println(classLoader);
		
		//2. 获取系统类加载器的父类加载器.
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		
		//3. 获取扩展类加载器的父类加载器.无法直接获取.
		classLoader = classLoader.getParent();
		System.out.println(classLoader);
		
		//4. 测试当前类由哪个类加载器进行加载:
		classLoader = Class.forName("com.atguigu.javase.lesson12.ReflectionTest").getClassLoader();
		System.out.println(classLoader);
		
		//5. 测试 JDK 提供的 Object 类由哪个类加载器负责加载
		classLoader = Class.forName("java.lang.Object").getClassLoader();
		System.out.println(classLoader);		
		
		//6. 关于类加载器的一个主要方法.
		// 调用 getResourceAsStream 获取类路径下的文件对应的输入流.
		InputStream in = null;
		in = this.getClass().getClassLoader().getResourceAsStream("test.properties");
		
				//new FileInputStream("test.properties");
		System.out.println(in);
		
	}
	
	
	/**
	 * Class 类的 newInstance() 方法.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testNewInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		
		String className = "com.atguigu.javase.lesson12.Person";
		Class clazz = Class.forName(className);
		
		// 利用 Class 对象的 newInstance() 方法来创建类的一个对象.
		// 实际调用的是类的无参数的构造器!
		// 一般的,一个类若声明了一个带参数的构造器,也要声明一个无参数的构造器.
		Object obj = clazz.newInstance();
		
		System.out.println(obj);
	}
	
	
	
	/**
	 * 关于Class:
	 * 1. Class 是一个类.
	 * 2. 对象照镜子后可以得到的信息：某个类的数据成员名、方法和构造器、
	 *    某个类到底实现了哪些接口。
	 * 3. 对于每个类而言，JRE 都为其保留一个不变的 Class 类型的对象。
	 *    一个 Class 对象包含了特定某个类的有关信息。
	 * 4. Class 对象只能由系统建立对象一个类在 JVM 中只会有一个Class实例 每个类的实例都会记得自己是由哪个 Class 实例所生成 
	 * @throws ClassNotFoundException 
	 * 
	 */
	
	@Test
	public void testClass() throws ClassNotFoundException {
		
		Class clazz = null;
		
		//1. 得到 Class 对象.
		//1.1 直接通过类名.class 的方式得到
		clazz = Person.class;
		
		//1.2 通过对象调用 getClass() 方法来获取.
		Object obj = new Person();
		clazz = obj.getClass();
		
		//1.3 通过全类名的方式获取,用的比较多.
		String className = "com.atguigu.javase.lesson12.Person";
		clazz = Class.forName(className);
		
		//Field[] fields = clazz.getDeclaredFields();
		System.out.println();
		
		
		
	}

}

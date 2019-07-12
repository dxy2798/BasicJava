package com.atguigu.javase.lesson12;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.ObjectInputStream.GetField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;
import org.junit.runners.model.Annotatable;

public class ReflectionTest4 {
	
	/**
	 * 反射小结:
	 * 1. Class: 是一个类; 是一个描述类的类. 封装了描述方法的 Method,
	 * 描述字段的 field; ,描述构造器的 Constructor 等属性.
	 * 
	 * 2. 如何得到 Class 对象:
	 * 2.1 Person.class       类名的获取
	 * 2.2 person.getClass()  对象的获取
	 * 2.3 Class.forName()   全类名的获取
	 *     Class.forName("com.atguigu.javase.lesson12.Person")  
	 * 
	 * 3. 关于 Method:
	 * 3.1  如何获取Method: 
	 * 1). getDeclaredMethods(): 得到 Method 的数组.
	 * 2). getDeclaredMethod(String name, methodClass ... parameterTypes) 
	 * 
	 * 3.2  如何调用Method: 
	 * 1). 如果方法是 private 修饰的,需要先调用 Method 的 setAccessible(true),
	 *     使其变为可访问.
	 * 2). method.invoke(obj,Object ... args);    
	 * 
	 * 4. 关于 Field:
	 * 4.1  如何获取 Field: getField(String fieldName)
	 * 4.2  如何获取 Field 的值:
	 * 1). setAccessible(true)
	 * 2). field.get(Object obj)
	 * 4.3  如何设置 Field 的值:
	 *     field.set(Object obj,Object val)
	 * 
	 * 5. 了解 Constructor 和  Annotation
	 * 
	 * 6. 反射和泛型:
	 * 6.1 getGenericSuperClass: 获取带泛型参数的父类,返回值为: BaseDao<Employee, String>
	 * 6.2 Type 的子接口: ParameterizedType
	 * 6.3  可以调用 ParameterizedType 的  Type[] getActualTypeArguments() 获取泛型
	 *     参数的数组.
	 *     
	 * 7. 搞定 ReflectionUtils 即可.   
	 * 
	 */
	
	
	
	
	
	
	
	
	
	 /**
	    * 通过反射, 获得 Class 定义中声明的父类的泛型参数类型
	    * 如: public EmployeeDao extends BaseDao<Employee, String>
	    * @param <T>
	    * @param clazz
	    * @return
	    */
	   @SuppressWarnings("unchecked")
	   public static<T> Class<T> getSuperGenericType(Class clazz){
	       return getSuperClassGenricType(clazz, 0);
	   }
	  /**
	    * 通过反射, 获得定义 Class 时声明的父类的泛型参数的类型
	    * 如: public EmployeeDao extends BaseDao<Employee, String>
	    * @param clazz: 子类对应的 Class 对象.
	    * @param index: 子类继承父类时传入的泛型的索引. 从 0 开始
	    * @return
	    */
	   @SuppressWarnings("unchecked")
	   public static Class getSuperClassGenricType(Class clazz, int index){
		   Type genType = clazz.getGenericSuperclass();
	       System.out.println(">>>>>>>" + genType);
	       if(!(genType instanceof ParameterizedType)){
	           return Object.class;
	       }
	       
	       Type [] params = ((ParameterizedType)genType).getActualTypeArguments();
	       
	       if(index >= params.length || index < 0){
	           return Object.class;
	       }
	       
	       if(!(params[index] instanceof Class)){
	           return Object.class;
	       }
	       
	       return (Class) params[index];
	   }
	   
	   public static Class getSuperClassGenricType1(Class clazz, int index){
		 //得到 clazz 的父类类型.
		   Type type = clazz.getGenericSuperclass();
		 //如果type 与 ParameterizedType 类型不相同.ParameterizedType表示参数化类型
		   if(!(type instanceof ParameterizedType)){
			   return null;
		   }
		   // 如果相同,就将 type 强转为 ParameterizedType
		   ParameterizedType parameterizedType = 
				   (ParameterizedType) type;
		   // 返回表示此类型实际类型参数的 Type 对象的数组
		   Type [] args = 
				   parameterizedType.getActualTypeArguments();
		   // 如果实际类型的参数数组为 null ,返回 null.
		   if(args == null){
			   return null;
		   }
		   // 所要求的索引小于 0 或者 大于参数数组的长度,返回 null.
		   if(index < 0 || index > args.length - 1){
			   return null;
		   }
		   
		   Type arg = args[index];
		   
		   if(arg instanceof Class){
			   return (Class)arg;
		   }
		   
		   return null;
	   }
	   
	   
	   @Test
	   public void testGetSuperClassGenricType(){
		   Class clazz = EmployeeDao.class;
		   //System.out.println(clazz);
		   // Employee.class
		   Class argClazz = getSuperClassGenricType(clazz, 0);
		   System.out.println(argClazz);
		   // String.class
		   argClazz = getSuperClassGenricType(clazz, 1);
		   System.out.println(argClazz);
		   
	   }
	   
	   
	   
	@Test
	public void testGenericAndReflection(){
		
		PersonDao personDao = new PersonDao();
		Person entity = new Person();
		
		personDao.save(entity);
		//class com.atguigu.javase.lesson12.Person
		Person result = personDao.get(1);
		
	}
	
	
	
	
	/**
	 * Annotation 和 反射:
	 * 1. 获取 Annotation:
	 * 1.1 getAnnotation(Class<T> annotationClass) 
	 * 1.2 getDeclaredAnnotations() 
	 * 
	 * 
	 */
	@Test
	public void testAnnotation() throws Exception{
		String className = "com.atguigu.javase.lesson12.Person";
		
		Class clazz = Class.forName(className);
		Object obj = clazz.newInstance();
		
		Method method = clazz.getDeclaredMethod("setAge", int.class);
		// 获取 AgeValidator
		int val = 25;
		
		Annotation annotation = method.getAnnotation(AgeValidator.class);
		
		if(annotation != null){
			if(annotation instanceof AgeValidator){
				
				AgeValidator ageValidator = (AgeValidator) annotation;
			
				if(val < ageValidator.min()  || val > ageValidator.max()){
					throw new RuntimeException("年龄非法");
				}
			
			}
			
			
			
			
		}
		
		method.invoke(obj, 10);
		System.out.println(obj);
	}
	
	/**
	 * Constructor: 代表构造器.
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Test
	public void testConstructor() throws SecurityException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String className = "com.atguigu.javase.lesson12.Person";
		Class<Person> clazz = (Class<Person>) Class.forName(className);
		//1. 获取 Constructor 对象.
		Constructor<Person> [] constructors = 
				(Constructor<Person>[]) Class.forName(className).getConstructors();
		
		for(Constructor<Person> constructor: constructors){
			System.out.println(constructor);
		}
		
		Constructor<Person> constructor =
				clazz.getConstructor(String.class,int.class);
		
		System.out.println(constructor);
		
		//2. 调用构造器的 newInstance() 方法创建对象.
		Object obj = constructor.newInstance("尚硅谷",1);
		System.out.println(obj);
	}
	
	@Test
	public void testClassField() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		String className = "com.atguigu.javase.lesson12.Student";
		String fieldName = "age"; // 可能为私有,可能在其父类中.
		Object val = 20;
		// 创建 className 对应类的对象,并为其 fieldName 赋值为 val
		Object obj = null;
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		Class clazz = Class.forName(className);
		Field field = null;
		
		for(Class clazz2 = clazz;clazz2 != Object.class;clazz2 = clazz2.getSuperclass()){
			
			try {
				field = clazz2.getDeclaredField(fieldName);
				
			} catch (Exception e) {} 
			
		}
		field.setAccessible(true);
		obj = clazz.newInstance();
		field.set(obj, val);
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		Student stu = (Student) obj;
		System.out.println(stu.getAge()); //20
	}
	
	
	/**
	 * Field: 封装了字段的信息.
	 * 1. 获取字段:
	 * 1.1 Field [] fields = clazz.getDeclaredFields();
	 * 1.2 Field field2 = clazz.getDeclaredField("age");
	 * 
	 * 2. 获取指定对象的指定字段的值.
	 * 	  public Object get(Object obj)
	 * 	  obj 为字段所在的对象.
	 * 	  Object val = field.get(person);
	 *    
	 * 注意: 若该字段是私有的,需先调用 setAccessible(true) 方法.
	 * 
	 * 3. 设置指定对象的指定字段的值:
	 * public void set(Object obj,Object value)
	 *  	  obj 为字段所在的对象.
	 *        value 为设置的值.
	 * field.set(person, "atguigu");     
	 * 
	 */
	@Test
	public void testField() throws ClassNotFoundException, NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, InvocationTargetException{
		String className = "com.atguigu.javase.lesson12.Person";
		Class clazz = Class.forName(className);
		
		//1. 获取字段.
		//1.1 获取 Field 的数组.
		
		Field [] fields = clazz.getDeclaredFields();
		
		for(Field field: fields){
			System.out.println(field.getName());
		}
		//1.2 获取指定名字的field.
		Field field = clazz.getDeclaredField("name");
		System.out.println(field.getName());
		
	
/*		Object [] args = {"尚硅谷",25};
		Class [] parameterTypes = getParamterTypes(args);
		Method method = getMethod(clazz, "setName", parameterTypes);
		Object result = invokeMethod(clazz.newInstance(), method, args);*/
		
		Person person = new Person("ABC", 12);
		//2. 获取指定对象的 Field 的值
		Object val = field.get(person);

		System.out.println(val);
		//3. 设置指定对象的 Field 的值
		field.set(person, "atguigu");
		System.out.println(person.getName());
		
		//4. 若该字段是私有的,需要调用 setAccessible(true) 方法.
		Field field2 = clazz.getDeclaredField("age");
		field2.setAccessible(true);
		System.out.println(field2.get(person));
	}
	
	
	/**
	 * 获取 clazz 的 methodName 方法,该方法可能是私有方法,还可能在父类中(私有方法)
	 * @param clazz
	 * @param methodName
	 * @param paramterTypes
	 * @return
	 */
	public Method getMethod(Class clazz,String methodName,Class ...paramterTypes){
		// 2. 调用 clazz 的 getDeclaredMethod() 方法获取 2 对应的 Method 对象.
		// 注意:
		// 2.1  因为该方法可能不在当前类中,所以有可能需要去父类中获取.
		// 2.2 因为给定的条件中并没有给定参数类型的列表,需要从 args 来获取参数类型的列表.
		for(;clazz != Object.class; clazz = clazz.getSuperclass()){
			try {
				Method method = clazz.getDeclaredMethod(methodName, paramterTypes);
				return method;
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return null;
	}
	
	/**
	 * 由 Object 数组得到对应的 Class 数组
	 * @param args
	 * @return
	 */
	public Class [] getParamterTypes(Object ... args){
		
		Class [] parameterTypes = new Class[args.length];
		for(int i = 0; i < args.length; i++){
			parameterTypes [i] = args[i].getClass();
		}
		return parameterTypes;
	}
	
	/**
	 * 执行 obj 对象的 method 方法, 参数值为 args
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Object invokeMethod(Object obj, Method method, Object[] args)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		// 3. 因为 Method 可能是私有的,所以需要让其变为可访问的: setAccessible(true)
		method.setAccessible(true);
		// 4. 调用 Method
		// 4.1 利用 1 得到的 clazz 先创建 className 对应的类的一个对象.
		//Object obj = clazz.newInstance();
		// 4.2 再调用 Method 的 invoke() 方法执行方法.
		Object result = method.invoke(obj, args);
		return result;
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	
	@Test
	public void testClassMethod() throws Exception{
		// 1.全类名
		String className = "com.atguigu.javase.lesson12.Student";
		// 2.方法名: 可能在1给的类中,也可能在父类中,可能是私有方法,也可能是共有方法.
		String methodName = "method3";
		// 3.执行 2 对应的方法时需要传入的参数列表. 
		Object [] args = {"尚硅谷",25};
		
		// 根据以上条件,执行 methodName 对应的方法,并打印返回值.
		
		// 1. 加载 className 对应的类,获取对应的 Class 对象 clazz.
		Class clazz = Class.forName(className);
		Class [] parameterTypes = getParamterTypes(args);
		
		Method method = getMethod(clazz, methodName, parameterTypes);
		Object result = invokeMethod(clazz.newInstance(), method, args);
		
		System.out.println(result.toString());
	}

	
	
	/**
	 * 
	 * @param obj: 某个类的一个对象.
	 * @param methodName: 类的一个方法的方法名.该方法也可能是私有方法.
	 * 					  还可能是该方法在父类中定义的(私有)方法
	 * @param args: 调用该方法需要传入的参数.
	 * @return: 调用方法后的返回值.
	 */
	
	@Test
	public void testInvoke3(){
		Object obj = new Student();
		invoke2(obj, "method1", 10);
		//Student 类的 method1() 方法被调用,打印 "private void method1"
		
		Object result = invoke3(obj,"method2");
		//Student 类的父类的 method2() 方法被调用,返回值为"private String method2"
		System.out.println(result);
	}
	
	public Object invoke3(Object obj,String methodName,
			Object ...args){
		
		//1. 获取 Method 对象.
		Class [] parameterTypes = new Class [args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = getMethod(obj.getClass(), methodName, parameterTypes);
			method.setAccessible(true);
			//2. 执行 Method 方法.
			//3. 返回方法的返回值.
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public Object invoke2(Object obj,String methodName,
			Object ...args){
		
		//1. 获取 Method 对象.
		Class [] parameterTypes = new Class [args.length];
		
		for(int i = 0; i < args.length; i++){
			parameterTypes[i] = args[i].getClass();
		}
		
		try {
			Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
			method.setAccessible(true);
			//2. 执行 Method 方法.
			//3. 返回方法的返回值.
			return method.invoke(obj, args);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	

	
	@Test
	public void testGetMethod() throws Exception{
		
		Class clazz = Class.forName("com.atguigu.javase.lesson12.Student");
		
		Method method = getMethod(clazz,"method1",Integer.class);
		System.out.println(method);
		
		method = getMethod(clazz, "method2");
		System.out.println(method);
	}
	
	@Test
	public void testInvoke2(){
		Object obj = new Student();
		invoke2(obj, "method1", 10);
		//Student 类的 method1() 方法被调用,打印 "private void method1"
		
		Object result = invoke2(obj,"method2");
		//Student 类的父类的 method2() 方法被调用,返回值为"private String method2"
		//System.out.println(result);
	}
	
	/**
	 * 若通过 Method 的 invoke() 调用方法,而访问权限不足,则可以先使该方法
	 * 变为可访问的:
	 * method.setAccessible(true);
	 * @throws Exception
	 */
	@Test
	public void testInvokePrivateMethod() throws Exception{
		Object obj = new Student();
		
		Class clazz = obj.getClass();
		Method method = clazz.getDeclaredMethod("method1",Integer.class);
		System.out.println(method);
		
		//若需要通过反射执行私有方法.
		method.setAccessible(true);
		method.invoke(obj, 10);
	}

}

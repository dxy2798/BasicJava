package day_8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.atguigu.javase.lesson7.MapTest;

/**
 * 关于泛型:
 * 1. 为什么需要泛型?
 * 1.1 使用集合时可能会发送安全问题: 可以向集合中放入任何类型.
 * 1.2 麻烦: 从集合中获取元素,需要进行类型的强制转换
 * 
 * 2. 使用泛型: 使用泛型方法, 泛型类.
 * 3. 定义泛型:
 * 3.1 定义泛型类: 人家咋定义,咱就咋定义
 * 3.1.1 声明类(接口)时,在类名的后面, 大括号的前面利用 <> 来声明泛型.
 *       在类中凡是可以使用类型的地方都可以使用类中声明的泛型.
 * 3.2 定义泛型方法:
 * 3.2.1: 在类(不一定是泛型类)中使用泛型方法.
 * 3.2.2: 在方法的返回值前面使用 <> 声明泛型类型. 则在方法的返回值、参数,方法体中
 * 		     都可以使用该类型.
 * 	      public <E> E getProperty(int id)
 * 
 * 4. 通配符:
 * 4.1 如果 Foo 是 Bar 的一个子类型(子类或者子接口). 而 G 是某种泛型声明,
 *     那么 G<Foo> 是 G<Bar> 的子类型并不成立: Person 是 Object 的子类型,但 List<Person>
 *     却不是 List<Object> 的子类!
 *     
 *     printPersons(List<Person> persons); 该方法的参数只能是
 *     Person 类型的 List,而不能是 Person 任何子类的 List,例如 Student 类型的 List.
 * 4.2 Collection<?>;     
 * 4.2.1 Collection<?> (发音为:"collection of unknown").
	            是各种 collections 的父类. 它的元素类型可以匹配任何类型. 显然,它被称为通配符.
 * 4.2.2 将任意元素加入到其中不是类型安全的, null 除外,因为 null 是所有类型的成员.
 * 4.3  Collection<? extends Person> persons 为带上限的 通配符: 该类型可以指向 Person类型
 *      及 Person 子类类型的集合,但也不能向其中放入 null 以外的任何元素.
 *     
 *     
 */


public class GenericTest {
	
	@Test
	public void testUseGeneric(){
		Collection<Person> persons = new ArrayList<>();
		
		persons.add(new Person());
		persons.add(new Person());
		persons.add(new Person());
		persons.add(new Person());
		
		// 调用 Collection 的 toArray 方法把该集合转为 Person 类型的数组
		Person[] personArray = persons.toArray(new Person[0]);
		System.out.println(personArray.length);
		
		Map<String, Person> personMap = new HashMap<String, Person>();
		personMap.put("AA", new Person("AA",12));
		personMap.put("BB", new Person("BB",13));
		personMap.put("CC", new Person("CC",14));
		personMap.put("DD", new Person("DD",15));
		
		for(Map.Entry<String, Person> entry: personMap.entrySet()){
			String key = entry.getKey();
			Person val = entry.getValue();
			System.out.println(key + "/" + val);
		}
		
		
	}
	
	@Test
	public void testGenericMethod(){
		Dao<Person> dao = new Dao<>();
		Person person = dao.get(1);
		
		String name = person.getName();
		
		String name2 = dao.getProperty(1);
		int age = dao.getProperty(2);
		
		dao.test("");
		dao.test(new Person());
		
	}
	
	@Test
	public void testGenericAndExtends(){
		
		Object [] objs = new Object[10];
		Person [] persons = new Person[10];
		// Object 是 Person 的父类, 则 Object 的数组也是Person 数组的父类.
		objs = persons;
		
		List<Object> objList = new ArrayList<>();
		List<Person> personList = new ArrayList<>();
		// Object 是 Person 的父类, 但 List<Object> 却不是 List<Person> 的父类.
		// 用反证法: 若可以,则意味着可以向 objList 中放入任何类型的对象,而从 personList
		// 中获取的却是 Person 类型的对象,这不可能.
		//objList = personList;
		
		printPersons(personList);
		
		List<Student> studentList = new ArrayList<>();
		// 不能传入 studentList ,理由同上.
		//printPersons(studentList);
		
		printPersons2(personList);
		printPersons2(studentList);
		
	}
	
	/**
	 * 1. Collection<?> (发音为:"collection of unknown").
	 * 	     是各种 collections 的父类. 它的元素类型可以匹配任何类型. 显然,它被称为通配符.
	 * 2. 将任意元素加入到其中不是类型安全的, null 除外,因为 null 是所有类型的成员.
	 */
	public void printCollection(Collection<?> coll){
		
	}
	
	
	/**
	 * 带通配符的集合的泛型声明
	 * 只要存在通配符,写入就是非法的.
	 * 
	 * public void addRectangle(List<? extends Shape> shapes){
	 * 
	 *   shapes.add(0,new Rectangle()); // compile-time error!
	 * }
	 * @param persons
	 */
	public void printPersons2(List<? extends Person> persons){
		
	}
	
	
	/**
	 * 要求传入的参数必须是 Person 类型的 List 或 Person 子类类型的 List
	 * 即: List<Person> persons 或 List<Student> students
	 * @param Persons
	 */
	public void printPersons(List<Person> Persons){
		
	}

	@Test
	public void testGenericClass() {
		
		Dao<Person> dao = new Dao<>();
		
		Person p = new Person();
		dao.save(p);
		
		
	}

}

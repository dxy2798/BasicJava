package com.atguigu.javase.lesson8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
/**
 * 不使用泛型:
 * 1. 集合中的类型并不安全,可以向集合中放入任何类型的对象.
 * 2. 从集合中取出的对象都是 Object 类型,在具体操作时可能需要进行类型的强制转换.
 * 	     在强制转换时也容易发生 ClassCastException.
 *
 */
public class GenericTest {

	@Test
	public void testCollectionWithGeneric() {

		Set<Person> persons = new TreeSet<>(new Comparator<Person>() {

			@Override
			public int compare(Person p1, Person p2) {
				return p2.getAge() - p1.getAge();
			}
		});
		
		persons.add(new Person("AA", 16));
		persons.add(new Person("BB", 13));
		persons.add(new Person("CC", 11));
		persons.add(new Person("DD", 18));
		
		Iterator<Person> it = persons.iterator();
		
		while(it.hasNext()){
			Person person = it.next();
			System.out.println(person.getAge());
		}
		
		
	}
	
	
	@Test
	public void helloGeneric() {
		
		List<Person> persons2 = new ArrayList<Person>();
		
		persons2.add(new Person("AA", 12));
		persons2.add(new Person("BB", 13));
		persons2.add(new Person("CC", 14));
		persons2.add(new Person("DD", 15));
		persons2.add(new Student()); // 可以放子类对象
		
		
		Person person2 = persons2.get(2);
		System.out.println(person2);
		
		
		
		System.out.println();
		List persons = new ArrayList();
		
		persons.add(new Person("AA", 12));
		persons.add(new Person("BB", 13));
		persons.add(new Person("CC", 14));
		persons.add(new Person("DD", 15));
		
		//persons.add("ABCD");
		
		for(int i = 0; i < persons.size(); i++){
			Object obj = persons.get(i);
			Person person = (Person) obj;
			System.out.println(person.getName());
		}
		
		
	}

}

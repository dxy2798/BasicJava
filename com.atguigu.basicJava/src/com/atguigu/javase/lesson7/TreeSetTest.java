package com.atguigu.javase.lesson7;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Test;

/**
 * 默认情況下 TreeSet 要求集合中的元素必須实现 Comparable 接口
 * Comparable 接口中只有一个方法
 * public int compareTo(T o): 若返回 0 ,代表两个元素相等,若返回正数,代表当前元素大;
 * 若返回负数,代表当前元素小.
 * 	public int compareTo(Object o) {
		if(o instanceof Person){
			Person person = (Person) o;
			return -this.age + person.age;
			//return this.name.compareTo(person.name);
		}else{
			throw new ClassCastException("不能转为 Person 类型");
		}
	}
 * TreeSet 会调用每个元素的 compareTo()方法去和集合中的每个已经有的元素比较
 * 进而决定每个元素在集合中的位置.
 */

public class TreeSetTest {
	
	public static int k;
	
	@Test
	public void testTreeSet(){
		Comparator comparator = new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Person1 && o2 instanceof Person1){
					Person1 p1 = (Person1) o1;
					Person1 p2 = (Person1) o2;
					
					return p2.getAge() - p1.getAge();
				}
					throw new ClassCastException("不能转为 Person1 类型");
			
			}
			
		};
		
		//2. 创建 TreeSet 对象,传入 Comparator接口的实现类.
		TreeSet set = new TreeSet(comparator);
		
		set.add(new Person1("AA",12));
		set.add(new Person1("CC",16));
		set.add(new Person1("EE",18));
		set.add(new Person1("DD",11));
		set.add(new Person1("BB",10));
		
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
//		Comparator comparator()
//		Object first()
		
		Object object = set.first();
		
		System.out.println(object);
		
//		Object last()
//		Object lower(Object e)
//		Object higher(Object e)
//		SortedSet subSet(fromElement,toElement)
//		SortedSet headSet(toElement)
//		SortedSet headSet(toElement)
		
		Set subSet = set.tailSet(new Person1("AA",12),false);
		
		System.out.println(subSet);
	}
	
	
	@Test
	public void testStudentSort(){
		
		Comparator comparator = new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Student1 && o2 instanceof Student1){
					Student1 stu1 = (Student1) o1;
					Student1 stu2 = (Student1) o2;
					if(k == 1){
						return stu1.getScore() - stu2.getScore();
					}else if(k == 2){
						return stu2.getScore() - stu1.getScore();
					}else if(k == 3){
						return stu1.getName().compareTo(stu2.getName());
					}else if(k == 4){
						return  stu2.getName().compareTo(stu1.getName());
					}					
				}
				
				throw new ClassCastException("无法转换为 Stedent1 类型");
			}
		};
		
		
		
		
		System.out.println("请选择排序方式:1.按分数升序 2.按分数降序 3.按名字升序 4.按名字降序");
		Scanner sc = new Scanner(System.in);
		k = sc.nextInt();
		System.out.println(k);
		TreeSet set = new TreeSet(comparator);
		
		for(int i = 0;i < 5; i++){
			System.out.println("请输入学生姓名: ");
			sc = new Scanner(System.in);
			String name = sc.next();
			
			System.out.println("请输入学生分数: ");
			sc = new Scanner(System.in);
			int score = sc.nextInt();	
			set.add(new Student1(name, score));
		}
		
		
		printSet(set);
	}
	
	
	
	
	@Test
	public void testTreeSetWithComparator(){
		
		//1. 创建 Comparator 接口的实现类对象.
		//   实现 compare(Object o1,Object o2) 方法来比较两个对象的大小.
		Comparator comparator = new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Person1 && o2 instanceof Person1){
					Person1 p1 = (Person1) o1;
					Person1 p2 = (Person1) o2;
					
					return p2.getAge() - p1.getAge();
				}
					throw new ClassCastException("不能转为 Person1 类型");
									
			}
			
		};
		
		//2. 创建 TreeSet 对象,传入 Comparator接口的实现类.
		TreeSet set = new TreeSet(comparator);
		
		set.add(new Person1("AA",12));
		set.add(new Person1("CC",16));
		set.add(new Person1("EE",18));
		set.add(new Person1("DD",11));
		set.add(new Person1("BB",10));
		
		printSet(set);
		
		
	}

	@Test
	public void testTreeSetWithComparable(){
		
		TreeSet set = new TreeSet();
		
		set.add(new Person("AA",12));
		set.add(new Person("CC",16));
		set.add(new Person("EE",18));
		set.add(new Person("DD",11));
		set.add(new Person("BB",10));
		
		printSet(set);

	}
	@Test
	public void testStudent(){
		int i = 0;
		TreeSet set = new TreeSet();
		Scanner scanner = new Scanner(System.in);
		while(i<5){
			System.out.println("请输入学生姓名: ");
			
			String name = scanner.next();
			
			System.out.println("请输入学生分数: ");
			scanner = new Scanner(System.in);
			int score = scanner.nextInt();	
			
			set.add(new Student(name, score));
			i++;
		}
	
		printSet(set);

	}
	private static void printSet(TreeSet set) {
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}

}

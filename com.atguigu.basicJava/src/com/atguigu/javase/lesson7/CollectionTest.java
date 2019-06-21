package com.atguigu.javase.lesson7;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;

/**
 * Collection: 集合
 * 
 * 1. 添加元素的.
 * 2. 获取元素的 & 查找指定元素的.
 * 3. 移除元素的.
 * 4. 工具方法.
 *
 */
public class CollectionTest {

	@Test
	public void testToolMethod(){
		Collection collection = new ArrayList();
		collection.add("ABC");
		Person p = new Person("Tom",12);
		collection.add(p);
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		//1. contains(Object o) 
//		boolean result = collection.contains(new Person("Jerry",11));
//		System.out.println(result);
		
		//2. containsAll(Collection<?> c) 
		Collection collection2 = new ArrayList();
		collection2.add("ABC");
		collection2.add(new Person("Mike",14));
		boolean result = collection.containsAll(collection2);
		//System.out.println(result);
		//3. isEmpty() 
		
		//System.out.println(collection.isEmpty());
		//4. toArray()
		Object[] objs = collection.toArray();
		System.out.println(objs.length);
		
		//toArray(T[] a) 
		
	}
	
	
	/**
	 * 1. clear() 清空集合
	 * 2. remove(obj) 清除指定的元素,通过 equals() 方法在集合中查找指定的元素
	 * 	     若存在则移除
	 * 3. removeAll(Collection coll): 移除 coll 中有的元素
	 * 4. retainAll(ollection coll): 保存 coll 中有的元素
	 */
	
	@Test	
	public void testRetainAll(){
		Collection collection = new ArrayList();
		collection.add("ABC");
		Person p = new Person("Tom",12);
		collection.add(p);
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		
		Collection collection2 = new ArrayList();
		collection2.add("DEF");
		collection2.add(new Person("Mike",14));
		
		collection.retainAll(collection2);
		System.out.println(collection.size());
		System.out.println(collection);
		
	}
	
	
	@Test
	public void testRemove(){
		Collection collection = new ArrayList();
		collection.add("ABC");
		
		Person p = new Person("Tom",12);
		collection.add(p);
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		System.out.println(collection.size());
//		collection.clear();
//		System.out.println(collection.size());
		
//		collection.remove(new Person("Jerry",13));
//		System.out.println(collection.size());
//		Iterator iterator = collection.iterator();
//		while(iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
		
		Collection collection1 = new ArrayList();
		collection1.add("ABC");
		
		Person p1 = new Person("Tom",12);
		collection1.add(p);
		
		collection.removeAll(collection1);
		
		Iterator iterator = collection.iterator();
		
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}
	
	
	/**
	 * 在Collection 中无法获取指定的元素,但可以遍历所有的元素
	 * 1. 使用增强的 for 循环
	 * 2. 使用 Iterator 迭代器
	 * 2.1  获取迭代器对象: 调用 Collection 的 iterator() 方法, 获取  Iterator 接口的对象.
	 * 2.2 调用  Iterator 接口的方法进行迭代.
	 */
	@Test
	public void testIterator(){
		Collection collection = new ArrayList();
		collection.add("ABC");
		collection.add(new Person("Tom",12));
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		
/*		for(Object obj: collection){
			System.out.println(obj);
		}*/
		
		Iterator it = collection.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
	}
	
	
	
	/**
	 * addAll(Collection coll): 添加一组元素到集合中.
	 */
	@Test
	public void voidAddAll(){
		Collection collection2 = new ArrayList();
		
		Collection collection = new ArrayList();
		collection.add("ABC");
		collection.add(new Person("Tom",12));
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		
		collection2.addAll(collection);
		System.out.println(collection2.size());
		
		
		
		
	}
	
	
	/**
	 * add(Object obj): 添加一个元素到集合中.
	 */
	@Test
	public void testAdd(){
		Collection collection = new ArrayList();
		
		System.out.println(collection.size());
		
		collection.add("ABC");
		
		collection.add(new Person("Tom",12));
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		
		System.out.println(collection.size());
		
	}
	

}

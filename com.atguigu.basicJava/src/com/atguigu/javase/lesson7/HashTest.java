package com.atguigu.javase.lesson7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 关于 HashSet
 * 1. HashSet 是 Set 的最典型实现
 * 2. HashSet 中不能有重复的元素. 判定两个元素相等的标准: equals() 方法返回 true
 * 3. HashSet 根据 hashCode() 值来存放元素, 所以不能保证元素的顺序.
 * 4. 如果两个对象通过 equals() 方法返回 true , 这两个对象的 hashCode 值也应该相同.
 * 5. HashSet 是线程不安全的.
 */

import org.junit.Test;

public class HashTest {
	
	@Test
	public void testHashSet(){
		
		Collection collection = new HashSet();
		collection.add("ABC");
		
		Person p = new Person("Tom",12);
		collection.add(p);
		collection.add(new Person("Jerry",13));
		collection.add(new Person("Mike",14));
		System.out.println(collection.size());
		
	
		Iterator iterator = collection.iterator();
		
		while(iterator.hasNext()){
			
			System.out.println(iterator.next().hashCode());
		}
		
	}
	

	@Test
	public void testAdd(){
		
		Person p1 = new Person("AA",12);
		
		Person p2 = new Person("AA",12);
		
//		System.out.println(p1.equals(p2));
//		System.out.println(p2.equals(p1));
		
		Set set = new HashSet<>();
		
		set.add(p1);
		set.add(p2);
		
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
	}
	
	

}

package com.atguigu.javase.lesson7;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CollectionsTest {

	@Test
	public void testCollections() {

		//1. 获取线程安全的集合对象
		//   调用 Collections 的 synchronizedXxx 方法获取线程安全的集合对象.
		List list = new ArrayList<>();
		List list2 = Collections.synchronizedList(list);
	
		//2. 排序的方法
		List list3 = new ArrayList();
		list3.add(new Person1("AA",12));
		list3.add(new Person1("BB",8));
		list3.add(new Person1("MM",16));
		list3.add(new Person1("RR",11));
		list3.add(new Person1("CC",19));
		
		for(Object obj : list3){
			System.out.println(obj);
		}
		
		System.out.println();
		
		Collections.sort(list3,new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Person1 && o2 instanceof Person1){
					Person1 p1 = (Person1) o1;
					Person1 p2 = (Person1) o2;
					return p2.getAge() - p1.getAge();
				}
					throw new ClassCastException("不能转为 Person1 类型");
			}
		});
		
		for(Object obj : list3){
			System.out.println(obj);
		}
		
		System.out.println();
		//3. 获取 List 中最小的元素
		//   要求集合中的元素都实现 Comparable 接口
		Set set = new HashSet();
		set.add(new Person("AA",12));
		set.add(new Person("BB",8));
		set.add(new Person("MM",16));
		set.add(new Person("RR",11));
		set.add(new Person("CC",19));
		
		Object obj = Collections.min(set);
		System.out.println(obj);
		
		
		
		
	}

}

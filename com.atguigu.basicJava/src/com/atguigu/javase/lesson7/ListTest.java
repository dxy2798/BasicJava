package com.atguigu.javase.lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import banking.testBank;

public class ListTest {
	
	
	public void test(String ...args){
		for(int i=0;i < args.length;i++){
			System.out.println(args[i]);
		}
	}
	
	public int adding(int ... args){
		int j = 0;
		for(int i=0;i < args.length;i++){
			j = j + i;
		}
		return j;
	}

	@Test
	public void testArraysAsList(){
		// 测试可变参数.
		//test("aa","bb","cc");
		//System.out.println(adding(1,2,3,4,5,6,7,8,9,10));
		
		List list = Arrays.asList("A","B","C");
		System.out.println(list);
		
		
	}
	
	
	@Test
	public void testArrayList(){
		List list = new ArrayList();
		
		list.add(new Person1("AA", 12));
		list.add(new Person1("BB", 11));
		list.add(new Person1("CC", 10));
		list.add(new Person1("DD", 9));
		list.add(new Person1("EE", 8));
		
		//List 独有的遍历方式
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		System.out.println();
		
		printList(list);
		
		// 获取指定元素的索引值
		int index = list.indexOf(new Person1("CC",10));
		System.out.println(index);
		
		//移除指定索引的元素
		list.remove(2);
		printList(list);
		
		System.out.println();
		
		//把元素添加到指定位置
		list.add(2,new Person1("FF", 20));
		printList(list);
		System.out.println("============");
		//获取重复元素最后的索引值
		list.add(new Person1("AA", 12));
		System.out.println(list.lastIndexOf(new Person1("AA",12)));
		
		
	}

	private void printList(List list) {
		Iterator it = list.iterator();
		
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	
	
}

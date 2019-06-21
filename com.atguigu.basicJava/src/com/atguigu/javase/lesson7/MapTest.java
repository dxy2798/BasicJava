package com.atguigu.javase.lesson7;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MapTest {

	/**
	 * 1. 添加
	 * 2. 查找 & 遍历
	 * 3. 删除
	 * 4. 工具方法
	 */
	@Test
	public void testTreeMap2(){
		
		Comparator comparator = new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				if(o1 instanceof Person1 && o2 instanceof Person1){
					Person1 p1 = (Person1) o1;
					Person1 p2 = (Person1) o2;
					return p2.getAge() - p1.getAge();
					}
				
				throw new ClassCastException("无法转换为 Person1 类型");
			}
		};
		Map map = new TreeMap(comparator);
		map.put(new Person1("CC", 10),"CC");
		map.put(new Person1("BB", 11),"BB");
		map.put(new Person1("AA", 12),"AA");
		map.put(new Person1("DD", 9),"DD");
		map.put(new Person1("EE", 8),"EE");
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object val = map.get(key);
			System.out.println(key + "|" + val);
		}
	}
	
	
	
	@Test
	public void testTreeMap(){
		
		Map map = new TreeMap();
		map.put("CC", new Person1("CC", 10));
		map.put("BB", new Person1("BB", 11));
		map.put("AA", new Person1("AA", 12));
		map.put("DD", new Person1("DD", 9));
		map.put("EE", new Person1("EE", 8));
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object val = map.get(key);
			System.out.println(key + "|" + val);
		
		}
		

		
	}
	
	@Test
	public void testLinkedHashMap(){
		Map map = new LinkedHashMap();
		// put: 添加元素
		map.put("CC", new Person1("CC", 10));
		map.put("BB", new Person1("BB", 11));
		map.put("AA", new Person1("AA", 12));
		map.put("DD", new Person1("DD", 9));
		map.put("EE", new Person1("EE", 8));
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object val = map.get(key);
			System.out.println(key + "|" + val);
		}
		
		
		
		
	}
	
	@Test
	public void testMap(){
		
		Map map = new HashMap();
		// put: 添加元素
		map.put("AA", new Person1("AA", 12));
		map.put("BB", new Person1("BB", 11));
		map.put("CC", new Person1("CC", 10));
		map.put("DD", new Person1("DD", 9));
		map.put("EE", new Person1("EE", 8));
		
		// bollean containsKey(Object key)
		// 是否包含指定的key
		
		System.out.println(map.containsKey("CC"));
		System.out.println(map.containsValue(new Person1("EE", 8)));
		//  V get(Object key) 
		System.out.println(map.get("CC"));
		
		
		
		//size(): 返回 Map 大小
		System.out.println(map.size());
		
		// Collection<V> values(): 返回 Value 对应的集合
		Collection values = map.values();
		System.out.println(values);
	
		// remove(Object key): 移除指定键对应的键值对
		map.remove("CC");
		System.out.println(map.size());
		
		//putAll(Map<? extends K,? extends V> m) 
		//放入一组键值对
		
		Map map1 = new HashMap<>();
		map1.put("FF", new Person1("FF", 10));
		map1.put("GG", new Person1("GG", 9));
		map1.put("KK", new Person1("KK", 8));
		
		map.putAll(map1);
		System.out.println(map.size());
		
		//对 Map 进行遍历
		System.out.println("=======");
		for(Object s: map.keySet()){
			System.out.println(s);
		}
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			Object val = map.get(key);
			
			System.out.println(key + "|" + val);
		}
	}

}

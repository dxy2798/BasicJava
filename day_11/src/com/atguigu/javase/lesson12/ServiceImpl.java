package com.atguigu.javase.lesson12;

import java.util.HashMap;
import java.util.Map;

public class ServiceImpl implements Service{

	private static Map<Integer, Person> persons = 
			new HashMap<Integer, Person>();
	
	public static Map<Integer, Person> getPerson(){
		return persons;
	}
	
	// 构造器里提供模拟的实现
	
	public ServiceImpl() {
		persons.put(1001, new Person(1001, "AAA"));
		persons.put(1002, new Person(1002, "BBB"));
	}
	
	
	@Override
	public void addNew(Person person) {
		persons.put(person.getId(),person);
	}

	@Override
	public void delete(Integer id) {
		
		if(id == 1001){
			throw new RuntimeException("1001 不能被删除");
		}
		persons.remove(id);
		
	}

	@Override
	public void update(Person person) {
		persons.put(person.getId(),person);
	}

}

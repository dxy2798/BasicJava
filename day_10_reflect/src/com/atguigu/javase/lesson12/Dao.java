package com.atguigu.javase.lesson12;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

import javax.lang.model.element.Parameterizable;

public class Dao<T> {

	private Class<T> clazz;
	
	public Dao() {
//		System.out.println("Dao's Constrctor...");
//		System.out.println(this);
//		System.out.println(this.getClass());
//		// 获取 Dao 子类的父类
//		Class clazz = this.getClass().getSuperclass();
//		System.out.println(clazz); // Dao
		
		//获取 Dao 子类的带泛型参数的父类: Dao<Person>
		Type type = this.getClass().getGenericSuperclass();
		//System.out.println(type);
		
		// 获取具体的泛型参数.
		if(type instanceof ParameterizedType){
			ParameterizedType parameterizedType = 
					(ParameterizedType) type;
			
			Type[] args = parameterizedType.getActualTypeArguments();
			//System.out.println(Arrays.asList(args));
			
			if(args != null && args.length > 0){
				Type arg = args[0];
				if(arg instanceof Class){
					clazz = (Class<T>) arg;
				}
			}
		}
	}
	
	
	T get(Integer id){
		System.out.println(clazz);
		return null;
	}
	
	void save(T entity){
		
	}
}

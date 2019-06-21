package lesson29_1;

import lesson29_1.OuterClass.InnerClass;
import lesson29_1.OuterClass.StaticInnerClass;

public class TestInnerClass {

	public static void main(String[] args) {
		//2. 在类的外部如何创建内部类对象
		//2.1 对于非静态内部类, 必须先创建外部类对象, 然后再创建
		//	  内部类对象.
		
		OuterClass outerClass = new OuterClass();
		InnerClass innerClass = outerClass.new InnerClass();
		
		innerClass.test();

		//2.2 静态内部类: 内部类使用 static 来修饰, 所以创建该类的对象可以
		//             没有外部类对象. 
		
		StaticInnerClass sInnerClass = new StaticInnerClass();
		sInnerClass.test();
		
		//1. 在类的内部直接创建一个 CompareObject 接口的实现类对象.
		//   匿名内部类
		
		CompareObject compareObject = new CompareObject() {
			
			@Override
			public int compareTo(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
	}

}

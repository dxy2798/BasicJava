package day_8_Season;

import org.junit.Test;
/**
 * 关于枚举类:
 * 
 * 1. 定义: 一个类的对象的个数是有限而且固定,属性固定.
 * 
 * 2. 手工定义枚举类: 
 * 2.1 私有化构造器.
 * 2.2 属性使用 final 来修饰.
 * 2.3 在类的内部创建枚举类的对象,且使用 public static final 修饰
 * 2.4  提供一些工具方法: values(), valueOf()	
 *   
 * 3. 使用 Enum 关键字定义枚举类: 
 * 3.1 使用 enum 替代 class 声明一个类.
 * 3.2 枚举类对象的声明必须放在枚举类的第一行.声明对象的同时即是创建枚举类对象的过程.
 * 		SPRING("春天", "春风又绿江南岸")
 * 3.3 若属性需要使用 static final 修饰,使其变为常量.
 * 3.4 构造器默认使用 private 修饰.
 * 
 * 4. 枚举类的常用方法:
 * 4.1 values() 得到所有枚举类对象的集合.
 * 4.2 valueOf(Class<T> enumType, String name) 根据传入的字符串得到对应的
 * 	   enumType 类型的枚举类的对象.
 *  
 * 5. 实现接口的枚举类: 
 * 5.1 统一在一个方法中提供各个枚举类对象的实现,可以使用 switch
 * 5.2 在声明对象的同时提供方法的实现.
 *
 */
public class EnumTest {
	
	@Test
	public void testEnumAndInterface4(){
		Season4 [] season4s = Season4.values();
		for(Season4 season4: season4s){
			System.out.println(season4.getInfo());
		}
	}
	@Test
	public void testEnumAndInterface(){
		Season3 s3 = Season3.SUMMER;
		String result = s3.getInfo();
		
		System.out.println(result);
	}
	
	@Test
	public void testEnumMethod(){
		//1. 遍历枚举类的方法: 每个枚举类都有一个 values() 方法,返回枚举类对象的数组
		Season2 [] season2s = Season2.values();
		
		for(Season2 season: season2s){
			System.out.println(season);
		}
		
		//2. 把一个字符串转为对应的枚举类对象
		String input = "SPRING";
		// 第一个参数:枚举类的类型;第二个参数:对应的字符串
		Season2 s = Enum.valueOf(Season2.class, input);
		System.out.println(s.SEASON_DESC);
		
		
		
	}
	
	
	
	@Test
	public void testEnum(){
		//Season2.AUTUMN.SEASON_DESC = "ABC";
		
		System.out.println(Season2.AUTUMN.SEASON_DESC);
	}

	@Test
	public void testSeason() {
		
		Season s1 = Season.SPRING;
		Season s2 = Season.SPRING;
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1.getSEASON_NAME());
		System.out.println(s1.getSEASON_DESC());
	}

}

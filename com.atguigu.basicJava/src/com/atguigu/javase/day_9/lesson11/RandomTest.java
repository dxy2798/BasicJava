package com.atguigu.javase.day_9.lesson11;

import java.util.Random;
import static java.lang.Math.*;
import org.junit.Test;

/**
 * Random: 中封装了随机相关的方法: 返回随机的基本数据类型.
 * Math: 中封装了常用的数学方法.
 * 静态导入. 基本语法: 
 * import static java.lang.Math.*;
 * 导入指定类的静态属性和静态方法.
 */
public class RandomTest {

	@Test
	public void testMath(){
		//System.out.println(Math.sin(Math.PI / 6));
		System.out.println(sin(PI/3));
	}
	
	@Test
	public void testRandom() {
		Random random = new Random();
		
		System.out.println(random.nextInt());
		System.out.println(random.nextInt(10));
	}

}

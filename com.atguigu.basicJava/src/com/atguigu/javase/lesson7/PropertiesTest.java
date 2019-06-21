package com.atguigu.javase.lesson7;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void testProperties() throws IOException {
		
		//读取 jdbc.properties
		
		//1. 创建 Properties 对象
			Properties properties = new Properties();
		
		//2. 调用 Properties 的 load() 方法加载属性文件对应的输入流
			InputStream inputStream = 
					PropertiesTest.class.getClassLoader()
						.getResourceAsStream("jdbc.properties");
			properties.load(inputStream);
		//3. 调用 getProperty(String key) 获取属性值
		
			String user = properties.getProperty("user");
			System.out.println(user);
	}

}

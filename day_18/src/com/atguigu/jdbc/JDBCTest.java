package com.atguigu.jdbc;

import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;
import com.mysql.jdbc.Driver;

public class JDBCTest {
	
	/**
	 * 通过 JDBC 向指定的数据表中插入一条记录
	 * 传入的sql可以是INSERT,UPDATE,DELETE.但不能是SELECT
	 * @throws 
	 */
	@Test
	public void testStatement(){
			Connection connection = null;
			Statement statement = null;
			String sql = null;
			try {
				//1. 获取数据库连接
				connection = getConnection2();
				//3. 准备插入的 SQL 语句
				sql = "insert into ajax.customers (name,email,birth) values('ch','ch@kerryeas','1990-11-01')";
				//4. 执行插入
				//1).获取操作 SQL 语句的 Statement 对象
				//   调用 Connection 的 createStatement()方法
				statement = connection.createStatement();
				//2).调用 Statement 对象的executeUpdate(sql)
				//   执行 SQL 语句进行插入 
				statement.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					//5. 关闭 Statement 对象
					if(statement != null)
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					//2. 关闭数据库连接
					try {
						if (connection != null)
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

	}
	
	@Test
	public void testConnection() throws Exception{
		System.out.print(getConnection2());
	}
	
	public Connection getConnection2() throws Exception{
		//1.准备连接数据库的 4 个字符串。
		
		//1). 创建 Properties 对象
			Properties properties = new Properties();
		//2). 获取 jdbc.properties 对应的输入流
			InputStream in = 
					this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		//3). 加载 2) 对应的输入流
			properties.load(in);
		//4). 具体决定 user,password 等 4 个字符串
			String driverClass = properties.getProperty("driver");
			String jdbcUrl = properties.getProperty("jdbcUrl");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
		//2. 加载数据库驱动程序(对应 Driver 实现类中有注册驱动的静态代码块。)
			Class.forName(driverClass);
		
		//3. 通过DriverManager 的 getConnection() 方法获得数据库连接.
			return DriverManager.getConnection(jdbcUrl,user,password);
		
	}
	/**
	 * DriverManager 是驱动的管理类
	 * @throws Exception 
	 */
	@Test
	public void testDriverManager() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//读取类路径下的 jdbc.properties 文件
		InputStream in = 
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		//加载数据库驱动程序（对应的Driver实现类中有注册驱动的静态代码块）
		Class.forName(driverClass);
		//实际应该按下面去写，但是因为Driver中有个静态代码块已经创建了当前类的实例，注册到DriverManager里了
		//DriverManager.registerDriver(Class.forName(driverClass).newInstance());
		Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
		System.out.print(connection);
	}
	
	/**
	 * 最原始的使用Driver的方法
	 **/
	
	@Test
	public void test() throws SQLException {
		
		Driver driver = new Driver();
		
		String url = "jdbc:mysql://192.168.3.188:3306/ajax";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "32147");
		Connection connection = driver.connect(url, info);
		
		System.out.print(connection);
		
		}
	/**
	 * 编写一个通用的方法，在不修改源程序的情况下，可以获得任何数据库的连接
	 * 解决方案：把数据库驱动 Driver 实现类的全类名、url、user、password放入一个
	 * 配置文件中，通过修改配置文件的方式实现和具体数据库解耦
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//读取类路径下的 jdbc.properties 文件
		InputStream in = 
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		
		Properties properties = new Properties();
		properties.load(in);
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("jdbcUrl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		//通过反射创建 Driver 对象
		Driver driver = (Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user",user);
		info.put("password", password);
		Connection connection = driver.connect(jdbcUrl, info);
		
		return connection;
	}
	
	@Test
	public void testGetConnection() throws Exception{
		System.out.print(getConnection());
	}
	}



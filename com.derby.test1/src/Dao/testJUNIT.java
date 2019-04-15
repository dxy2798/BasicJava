package Dao;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.jdbc.Connection;

public class testJUNIT {

	/**
	 * 
	 * Driver是一个接口，数据库厂商必须提供实现的接口，能从其中获取数据库连接
	 * 可以通过 Driver 的实现类对象获取数据库连接，
	 * 1、加入数据库驱动
	 * @throws SQLException 
	 */
	@Test
	public void testDriver() throws SQLException {
		//第一种方式使用 Driver 连接数据库
		//1.创建一个 Driver 实现类的对象
		Driver driver = new com.mysql.jdbc.Driver();
		//2.准备连接数据库的基本信息：url,user,password
		String url = "jdbc:mysql://192.168.3.26:3306/daily_work";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "32147");
		//3.调用 Driver 接口的 connect(url,info)获取数据库连接
		java.sql.Connection connection = driver.connect(url,info);
		//System.out.print(connection);
	}
	

	
	
	
	/**
	 * 编写一个通用方法，在不修改源程序的情况下，可以获取任何数据库的连接
	 * 解决方案: 把数据库驱动 Driver 实现类的全类名、url、user、password 放进一个
	 * 配置文件中，通过修改配置文件的方法实现和具体的数据库解耦
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public Connection getConnection() throws InstantiationException, 
		IllegalAccessException, ClassNotFoundException, SQLException, IOException{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//读取类路径下的 jdbc.properties
		InputStream in =
				getClass().getClassLoader().getResourceAsStream("jdbc.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		Driver driver = 
				(Driver) Class.forName(driverClass).newInstance();
		Properties info = new Properties();
		info.put("user", user);
		info.put("password", password);
		Connection connection = (Connection) driver.connect(jdbcUrl, info);
		
		return connection;
	}
	
	@Test
	public void testGetConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, IOException{
		System.out.print(getConnection());
	}
	
	/**
	 * 利用driverManager改写 getConnection
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	@Test
	public Connection GetConnection2() throws IOException, ClassNotFoundException, SQLException{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//读取类路径下的 jdbc.properties
		InputStream in =
				getClass().getClassLoader().getResourceAsStream("jdbc3.properties");
		Properties properties = new Properties();
		properties.load(in);
		
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		Class.forName(driverClass);
		
		Connection conn = (Connection) DriverManager.getConnection(jdbcUrl, user, password);
		return conn;
	}
	


	
	/**
	 * DriverManager 是驱动的管理类
	 * @throws Exception 
	 * 
	 * 
	 */
	@Test
	public void testDriverManager() throws Exception{
		//1.准备连接数据库的4个字符串.
		//驱动的全类名.
		String driverClass = null;
		//JDBC URL
		String jdbcUrl = null;
		//user
		String user = null;
		//password
		String password = null;
		//读取类路径下的 jdbc.properties 文件
		InputStream in =
				getClass().getClassLoader().getResourceAsStream("jdbc3.properties");
		
		Properties properties = new Properties();
		properties.load(in);
		
		driverClass = properties.getProperty("driver");
		jdbcUrl = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
		//2.加载数据库驱动程序(注册驱动)强制JVM将driverClass这个类加载入内存，以便将其注册到DriverManager类上去
		Class.forName(driverClass);
		//3. 通过 DriverManager的getConnection() 方法获取数据库连接
		Connection conn = 
				(Connection) DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(conn);
	}
	
	/**
	 * 通过 JDBC 向指定数据表中插入一条记录
	 * @return 
	 * @throws Exception 
	 */
	@Test
	public void testStatement() throws Exception{
		//1.获取数据库连接.
		   Connection conn = GetConnection2();
		//2.准备插入的 SQL 语句.
		   String sql = "INSERT INTO CUSTOMERS (NAME,EMAIL,BIRTH) "
		   		+ "VALUES('XYZ','xyz@atguigu.com','1990-12-12')";
		//3.执行插入.
		   
		   //1) 获取操作 SQL 语句的 Statement 对象:
		   //   调用 Connection 的 createStatement() 方法.
		   		Statement stmp = conn.createStatement();
		   //2) 调用 Statement 对象的 executeUpdate(sql) 执行 SQL
		   //   语句进行插入操作.
		   		stmp.executeUpdate(sql);
		//4.关闭 Statement 对象.
		   		stmp.close();
		//5.关闭数据库连接.	
		   		conn.close();
	}

}

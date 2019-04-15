package Dao;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;

public class JDBCTestDerby {
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
		Connection connection = driver.connect(url,info);
		System.out.print(connection);
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
				getClass().getClassLoader().getResourceAsStream("jdbc3.properties");
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
		Connection connection = driver.connect(jdbcUrl, info);
		
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
	
	public Connection getConnection2() throws IOException, ClassNotFoundException, SQLException{
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
		
		Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
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
				DriverManager.getConnection(jdbcUrl, user, password);
		System.out.println(conn);
	}
	
	/**
	 * 通过 JDBC 向指定数据表中插入一条记录
	 * @return 
	 * @throws Exception 
	 */
	@Test
	public void testStatement() throws Exception{
		Connection conn = null;
		Statement stmp = null;
		try{
			//1.获取数据库连接.
		    conn = getConnection2();
		 //2.准备插入的 SQL 语句.
		   //String sql = "INSERT INTO nick.CUSTOMERS (NAME,EMAIL,BIRTH) "
		   //		+ "VALUES('deng','deng@atguigu.com','1972-02-11')";
		   //	String sql = "DELETE FROM nick.CUSTOMERS WHERE ID = 3";
		   	String sql = "UPDATE nick.customers set NAME = 'dengy' WHERE ID = 2";	
		 //3.执行插入.
		   //1) 获取操作 SQL 语句的 Statement 对象:
		   //   调用 Connection 的 createStatement() 方法.
		   stmp = conn.createStatement();
		   //2) 调用 Statement 对象的 executeUpdate(sql) 执行 SQL
		   //   语句进行插入操作.
		   		stmp.executeUpdate(sql);
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				//4.关闭 Statement 对象.
				if(stmp != null)
					stmp.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
		   		//5.关闭数据库连接.
				if(conn != null)
		   		conn.close();
			}
		}
	}
	
	/**
	 * 通用的更新的方法:包括INSERT、UPDATE、DELETE
	 * 版本1.0
	 */
	public void update(String sql){
		Connection conn = null;
		Statement stmp = null;
		
		try {
			conn = getConnection2();
			stmp = conn.createStatement();
			stmp.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(stmp != null){
				try{
					stmp.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			if(conn != null){
				try{
					stmp.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}
	}
	
	@Test
	public void testJDBCToolGetConnection() throws Exception{
		Connection conn = JDBCTools.getConnection();
		System.out.println(conn);
		Statement stmp = conn.createStatement();
		System.out.println(stmp);
		JDBCTools.releaseSource(stmp, conn);
		System.out.println("所有资源已关闭！");
	}
	
	/**
	 * ResultSet: 结果集，封装了JDBC 进行查询的结果。
	 * 1. 调用 Statement 对象的 executeQuery(sql) 可以得到结果集
	 * 2. ResultSet 返回的实际上就是一张数据表，有一个指针指向数据表的第一行的前面。
	 *    可以调用 next() 方法检测下一行是否有效，若有效该方法返回true，且指针下移。
	 *    相当于 Iterator 对象的 hasNext() 和 next() 方法的结合体
	 * 3. 当指针定位到一行时，可以调用 getXxx(index) 或 getXxx(columnName)
	 *    获取每一列的值，例如: getInt(1),getString("name")
	 * 4. ResultSet 当然也需要关闭。     
	 */
	@Test
	public void testResultSet(){
		Connection conn = null;
		Statement stmp = null;
		ResultSet rs = null;
		try{
			String sql = "select * from nick.customers";
			conn = JDBCTools.getConnection();
			stmp = conn.createStatement();
			rs = JDBCTools.query(sql,stmp,conn);
			while(rs.next()){
				System.out.println(rs.getString(2) + "|" + rs.getString(3) + "|" + rs.getDate(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(rs,stmp, conn);
		}
	}
	
	@Test
	public void testUpdate(){
		Connection conn = null;
		Statement stmp = null;
		String sql = null;
		try{
			//sql = "INSERT INTO nick.CUSTOMERS (NAME,EMAIL,BIRTH) "
			//		  		+ "VALUES('Helen','Helen@atguigu.com','1900-1-1')";
			//	String sql = "DELETE FROM nick.CUSTOMERS WHERE ID = 3";
		   	sql = "UPDATE nick.customers set NAME = 'X' WHERE id = 3";	
			JDBCTools.update(sql);
			System.out.println("OK!");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.releaseSource(stmp, conn);
		}
	}
	
}

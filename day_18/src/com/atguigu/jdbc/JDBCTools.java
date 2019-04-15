package com.atguigu.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 操作 JDBC 的工具类，其中封装了一些工具方法
 * Version 1.0
 * @author stephen
 *
 */
public class JDBCTools{

	/**
	 * 1. 获取连接的方法。
	 *    通过读取配置文件从数据库服务器获取一个连接
	 * @return Connection
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		String driverClass = null;
		String jdbcUrl = null;
		String user = null;
		String password = null;
		//读取类路径下的 jdbc.properties 文件
		InputStream in = 
				JDBCTools.class.getClassLoader().getResourceAsStream("jdbc.properties");
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
	
	/**
	 * 通用的更新的方法: 包括INSERT,UPDATE,DELETE
	 * 版本:1
	 */
	public void update(String sql){
		Connection connection = null;
		Statement statement = null;
		
		try{
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			statement.execute(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null,statement, connection);
		}
		
	}
	
	/**
	 * 关闭 ResultSet、 Statement 和 Connection 
	 */
	public static void release(ResultSet rs,Statement statement,Connection connection){
		
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(statement != null)
		  try {	
			statement.close();
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		
		if (connection != null)
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
}

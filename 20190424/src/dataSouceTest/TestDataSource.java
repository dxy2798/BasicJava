package dataSouceTest;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

public class TestDataSource {

	/**
	 * 1. 加载 dbcp 的 properties 配置文件: 配置文件中的键来自 BasicDataSource 的属性
	 * 2. 调用 BasicDataSourceFactory 的 createDataSource 方法创建 DataSource 实例
	 * 3. 从 DataSource 实例中获取数据库连接
	 * @throws Exception 
	 */
	@Test
	public void testDBCPWithDataSourceFactory() throws Exception{
		
		Properties properties = new Properties();
		
		InputStream inStream = 
				TestDataSource.class.getClassLoader().getResourceAsStream("dbcp.properties");
		
		properties.load(inStream);
		
		BasicDataSource dataSource = 
				BasicDataSourceFactory.createDataSource(properties);

		System.out.println(dataSource.getConnection());
		
		BasicDataSource basicDataSource = dataSource;
		System.out.println(basicDataSource.getMaxWaitMillis());
	}
	
	
	
	
	/**
	 * 使用 DBCP 数据库连接池
	 * 1. 加入 jar 包 commons-dbcp2-2.2.0.jar 依赖于 commons-pool2-2.5.0.jar
	 * 2. 创建数据库连接池
	 * @throws SQLException 
	 */
	@SuppressWarnings("resource")
	@Test

	public void testDBCP() throws SQLException{
		final BasicDataSource dataSource = new BasicDataSource();
		
		//2. 为数据源实例指定必须的属性
		dataSource.setUsername("root");
		dataSource.setPassword("32147");
		dataSource.setUrl("jdbc:mysql://192.168.3.26:3306/ajax");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//3. 指定数据源的一些可选的属性.
		//1). 指定数据库连接池中初始化连接数的个数
		dataSource.setInitialSize(5);
		
		//2). 指定最大的连接数: 同一时刻可以同时向数据库申请的连接数
		dataSource.setMaxTotal(5);
		
		//3). 指定小连接数: 在数据库连接池中保存的最少的空闲连接的数量 
		dataSource.setMinIdle(2);
		
		//4).等待数据库连接池分配连接的最长时间. 单位为毫秒. 超出该时间将抛出异常. 
		dataSource.setMaxWaitMillis(1000 * 5);
		
		//4. 从数据源中获取数据库连接
		Connection connection = dataSource.getConnection();
		System.out.println("1" + connection.getClass()); 
		
		connection = dataSource.getConnection();
		System.out.println("2" + connection.getClass()); 
		
		connection = dataSource.getConnection();
		System.out.println("3" + connection.getClass()); 
		
		connection = dataSource.getConnection();
		System.out.println("4" + connection.getClass()); 
		
		Connection connection2 = dataSource.getConnection();
		System.out.println("5" + connection2.getClass()); 
		
		new Thread(){
			public void run() {
				Connection conn;
				try {
					conn = dataSource.getConnection();
					System.out.println("6" + conn.getClass()); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		connection2.close();
	}

}

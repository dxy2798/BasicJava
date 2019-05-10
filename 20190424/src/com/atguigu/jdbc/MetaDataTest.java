package com.atguigu.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.junit.Test;

import com.mysql.jdbc.DatabaseMetaData;

public class MetaDataTest {
	
	/**
	 * 读取 blib 数据:
	 * 1. 使用 getBlob 方法读取到 Blob 对象
	 * 2. 调用 Blob 的 getBinaryStream() 方法得到输入流. 再使用 IO 操作即可.
	 */
	@Test
	public void readBlob(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name customer_name,email,birth,picture "
					+ "FROM ajax.customers WHERE id = 13";
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				Date birth = resultSet.getDate(4);
				System.out.println(id + "|" + name + "|" + email  + "|" + birth);
				Blob picture = resultSet.getBlob(5);
				
				InputStream in = picture.getBinaryStream();
				//OutputStream out = new FileOutputStream("C:\\Users\\Public\\Desktop\\x1.jpg");
				OutputStream out = new FileOutputStream("C:\\Users\\Public\\Desktop\\1.jpg");
				byte [] buffer = new byte[1024];
				int len = 0;
				while((len = in.read(buffer)) != -1){
					System.out.println(len);
					//System.out.println(len + "|" + in.read(buffer));
					out.write(buffer,0,len);
				}
				out.flush();
				out.close();
				in.close();
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
	}
	
	/**
	 * 插入 BLOB 类型的数据必须使用 PreparedStatement
	 * 调用 setBlob(int index,InputStream inputStream) 
	 */
	@Test
	public void  testInsertBlob() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO customers(name,email,birth,picture) "
					+ "VALUES(?,?,?,?)";
			// 使用重载的 preparedStatement(sql,flag)
			// 来生成 preparedStatement 对象
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,"BCDE");
			preparedStatement.setString(2,"bcde@atguigu.com");
			preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
			
			InputStream inputStream = new FileInputStream("20190110091406.jpg");
			preparedStatement.setBlob(4, inputStream);
			preparedStatement.executeUpdate();
			inputStream.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.releaseSource(preparedStatement, connection);
		}

	}

	/**
	 * 取得数据库自动生成的主键
	 */
	@Test
	public void testGetKeyValue(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO customers(name,email,birth) "
					+ "VALUES(?,?,?)";
			// 使用重载的 preparedStatement(sql,flag)
			// 来生成 preparedStatement 对象
			preparedStatement = connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,"ABCDE");
			preparedStatement.setString(2,"abcde@atguigu.com");
			preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
			preparedStatement.executeUpdate();
			// 通过 .getGeneratedKeys() 获取包含了新生成的主键的 ResultSet 对象
			// 在 ResultSet 中只有一列 GENERATED_KEY,用于存放新生成的主键值
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				System.out.println(rs.getObject(1));
			}
			
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 0; i < rsmd.getColumnCount(); i++){
				System.out.println(rsmd.getColumnName(i + 1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.releaseSource(preparedStatement, connection);
		}
		
	}
	/**
	 * ResultSetMetaData 描述结果集的元数据
	 * 可以得到结果集中的基本信息，有哪些列，列名，列别名
	 */
	@Test
	public void testResultSetMetaData() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name customer_name,email,birth "
					+ "FROM ajax.customers";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			//2. 得到列的个数
				int columnCount = rsmd.getColumnCount();
				System.out.println(columnCount);
			
			    for(int i = 0;i < columnCount; i++){
			    	//3. 得到列名
			    	String columnName = rsmd.getColumnName(i + 1);
					//4. 得到列的别名
			    	String columnLabel = rsmd.getColumnLabel(i + 1);
			    	System.out.println(columnName + "|" + columnLabel);
			    	
			    }

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}

	}
	
	/**
	 * DatabaseMetaData 是描述数据库的元数据对象
	 * 可以由 Connection 得到。
	 */
	@Test
	public void testDatabaseMetaData(){
		Connection connection = null;
		ResultSet resultSet = null;		
		try{
			connection = JDBCTools.getConnection();
			DatabaseMetaData data = (DatabaseMetaData) connection.getMetaData();
			// 数据库的版本号
			int version = data.getDatabaseMajorVersion();
			System.out.println(version);
			// 得到连接到数据库的用户名
			String username = data.getUserName();
			System.out.println(username);
			// 得到 MySQL 中有哪些数据库
			resultSet = data.getCatalogs();
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet,null,connection);
		}
	}

}

package com.atguigu.jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.Scanner;;

public class reviewTest {

	@Test
	public void testGetPreparedStatement(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO NICK.CUSTOMERS(name,email,birth) VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "ATGUIGU");
			preparedStatement.setString(2, "simpleit@163.com");
			preparedStatement.setDate(3,
					new Date(new java.util.Date().getTime()));
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.releaseSource(preparedStatement, connection);
		}
	}
	
	/**
	 * SQL注入测试
	 */
	@Test
	public void testSQLInjection(){
		String username = "a' OR password = ";
		String password = " OR '1' = '1";
		String sql = "SELECT * FROM nick.users WHERE "
				+ "username = '" + username + "' AND password = '" + password + "'";
		System.out.println(sql);
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try{
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				System.out.println("登录成功!");
			}else{
				System.out.println("用户名和密码不匹配或用户名不存在!");
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, statement, connection);
		}
	}
	
//====================控制台输入信息查询学生================================================

	
	
//====================================================================================	
	
}

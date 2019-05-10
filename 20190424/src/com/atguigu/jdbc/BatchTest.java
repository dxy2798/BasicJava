package com.atguigu.jdbc;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import org.junit.Test;

public class BatchTest {

	/**
	 * 1. 使用 Statemet
	 */
	@Test
	public void testBatchWithStatement() {
		
		Connection connection = null;
		Statement statement = null;
		
		try{
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);
			statement = connection.createStatement();
			long begin = System.currentTimeMillis();
			for(int i=0;i<100000;i++){
				String sql = "INSERT INTO ajax.custormer(id,name,email) "
						+ "VALUE(" + (i+1) + ",'name_" + i + "','@163.com')";
				statement.executeUpdate(sql);
			}
			JDBCTools.commit(connection);
			long end = System.currentTimeMillis();
			System.out.println("Time: " + (end - begin));
		}catch(Exception e){
			e.printStackTrace();
			JDBCTools.rollback(connection);
		}finally{
			JDBCTools.releaseSource(statement, connection);
		}
	}
	
	/**
	 * 2. 使用 PrepareStatement
	 */
	@Test
	public void testBatchWithPrepareStatement() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO ajax.custormer(id,name,email) VALUE(?,?,?)";
		try{
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);
			preparedStatement = connection.prepareStatement(sql);
			long begin = System.currentTimeMillis();
			for(int i = 0;i < 100000; i++){
				
				preparedStatement.setInt(1, i + 1);
				preparedStatement.setString(2, "name_" + i);
				preparedStatement.setString(3, "@163.com");
				preparedStatement.executeUpdate();
			}
			JDBCTools.commit(connection);
			long end = System.currentTimeMillis();
			System.out.println("Time: " + (end - begin));
		}catch(Exception e){
			e.printStackTrace();
			JDBCTools.rollback(connection);
		}finally{
			JDBCTools.releaseSource(preparedStatement, connection);
		}
	}
	
	/**
	 * 3.使用Batch
	 */
	@Test
	public void testBatch(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = null;
		
		try {
			connection = JDBCTools.getConnection();
			JDBCTools.beginTx(connection);
			sql = "INSERT INTO ajax.custormer(id,name,email) VALUE(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			//Date date = new Date(new java.util.Date().getTime());
			
			long begin = System.currentTimeMillis();
			for(int i = 0; i < 100000; i++){
				preparedStatement.setInt(1, i + 1);
				preparedStatement.setString(2, "name_" + i);
				preparedStatement.setString(3, i + "@163.com");
				
				//"积攒" SQL 
				preparedStatement.addBatch();
				
				//当 "积攒" 到一定程度, 就统一的执行一次. 并且清空先前 "积攒" 的 SQL
				if((i + 1) % 300 == 0){
					preparedStatement.executeBatch();
					preparedStatement.clearBatch();
				}
			}
			
			//若总条数不是批量数值的整数倍, 则还需要再额外的执行一次. 
			if(100000 % 300 != 0){
				preparedStatement.executeBatch();
				preparedStatement.clearBatch();
			}
			
			long end = System.currentTimeMillis();
			
			System.out.println("Time: " + (end - begin)); //569
			
			JDBCTools.commit(connection);
		} catch (Exception e) {
			e.printStackTrace();
			JDBCTools.rollback(connection);
		} finally{
			JDBCTools.release(null, preparedStatement, connection);
		}
	}
	
}

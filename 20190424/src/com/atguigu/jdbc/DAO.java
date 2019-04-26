package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

public class DAO {
	
	//INSERT,UPDATE,DELETE 操作都可以包含其中
	public void update(String sql,Object ... args){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			for(int i = 0;i<args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			
			preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, preparedStatement, connection);
		}
	}
	
	//查询一条记录，返回对应的对象
	public static <T> T get(Class<T> clazz,String sql,Object ... args){
		
		T entity = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			
			resultSet = preparedStatement.executeQuery();

			
			if(resultSet.next()){
				Map<String, Object> values = new HashMap<String, Object>();
				ResultSetMetaData rsmd = resultSet.getMetaData();
				
				int columnCount = rsmd.getColumnCount();
				
				for(int i = 0;i < columnCount;i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(columnLabel);
					values.put(columnLabel, columnValue);
				}
				entity= clazz.newInstance();
				
				for(Map.Entry<String, Object> entry: values.entrySet()){
					String fieldName = entry.getKey();
					Object fieldValue = entry.getValue();
					ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		return entity;
	}
	//查询多条记录，返回对应的对象的集合
	
	//返回某条记录的某个字段的值 或 一个统计的值(一共有多少记录等)
}

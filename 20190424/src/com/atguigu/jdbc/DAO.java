package com.atguigu.jdbc;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

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
					//ReflectionUtils.setFieldValue(entity, fieldName, fieldValue);
					BeanUtils.setProperty(entity, fieldName, fieldValue);
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
	public <T> List<T> getForList(Class<T> clazz,String sql,Object ... args){
		List<T> list = new ArrayList<T>();
		
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
			
			List<Map<String, Object>> values = new ArrayList<>();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			Map<String, Object> map = null;	
			
			while(resultSet.next()){
				
				map = new HashMap<>();
				for(int i = 0; i < rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object value = resultSet.getObject(i + 1);
					map.put(columnLabel, value);
				}
				values.add(map);
			}
			
			T bean = null;
			
			if(values.size() > 0){
				for(Map<String, Object> m:values){
					bean = clazz.newInstance();
					for(Map.Entry<String, Object> entry:m.entrySet()){
						String propertyName = entry.getKey();
						Object value = entry.getValue();
						BeanUtils.setProperty(bean, propertyName, value);
					}
					list.add(bean);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		
		return list;
	}
	//返回某条记录的某个字段的值 或 一个统计的值(一共有多少记录等)
	public <F> F getForValue(String sql,Object ... args){
		
		return null;
	}
	
	
}

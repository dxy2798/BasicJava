package com.atguigu.jdbc;

import static org.hamcrest.CoreMatchers.nullValue;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
	
	/**
	 * 查询一条记录，返回对应的对象
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
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
	
	
	/**
	 * 查询多条记录，返回对应的对象的集合
	 * @param clazz
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> List<T> getForList(Class<T> clazz,String sql,Object ... args){
		List<T> list = new ArrayList<T>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
			//1. 得到结果集
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			
			//2. 处理结果集,得到 Map 的 List,其中一个 Map 对象就是一条记录.
			//   Map 的 key 为 resultSet 中列的别名, Map 的value为列的值.
			List<Map<String, Object>> values = handleResultSetToMapList(resultSet);
			
			//3. 把 Map 的 List 转为 clazz 对应的 List
			//   其中 Map 的 key 即为 clazz 对应的对象的 propertyName, 
			//   而 Map 的 value 即为 clazz 对应的对象的 propertyValue
			list = transfterMapListToBeanList(clazz, values);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		
		return list;
	}

	public <T> List<T> transfterMapListToBeanList(Class<T> clazz, List<Map<String, Object>> values)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		
		List<T> result = new ArrayList<>();
		
		T bean = null;
		
		if(values.size() > 0){
			for(Map<String, Object> m:values){
				bean = clazz.newInstance();
				for(Map.Entry<String, Object> entry:m.entrySet()){
					String propertyName = entry.getKey();
					Object value = entry.getValue();
					BeanUtils.setProperty(bean, propertyName, value);
				}
				result.add(bean);
			}
		}
		return result;
	}
	/**
	 * 处理结果集，得到 Map 的一个 List，其中一个 Map 对象对应一条记录
	 * @param resultSet
	 * @throws SQLException
	 */

	public List<Map<String, Object>> handleResultSetToMapList(
			ResultSet resultSet) throws SQLException {
		// 5. 准备一个 List<Map<String, Object>>:
		// 键: 存放列的别名, 值: 存放列的值. 其中一个 Map 对象对应着一条记录
		List<Map<String, Object>> values = new ArrayList<>();

		List<String> columnLabels = getColumnLabels(resultSet);
		Map<String, Object> map = null;

		// 7. 处理 ResultSet, 使用 while 循环
		while (resultSet.next()) {
			map = new HashMap<>();

			for (String columnLabel : columnLabels) {
				Object value = resultSet.getObject(columnLabel);
				map.put(columnLabel, value);
			}

			// 11. 把一条记录的一个 Map 对象放入 5 准备的 List 中
			values.add(map);
		}
		return values;
	}
	
	/**
	 * 取得 ResultSet 的字段别名List
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private List<String> getColumnLabels(ResultSet rs) throws SQLException{
		List<String> labels =  new ArrayList<>();
		ResultSetMetaData rsmd = rs.getMetaData();
				for(int i = 0;i < rsmd.getColumnCount(); i++){
					 labels.add(rsmd.getColumnLabel(i + 1));
				}
		
		return labels;
	}
	
	
	//返回某条记录的某个字段的值 或 一个统计的值(一共有多少记录等)
	public <F> F getForValue(String sql,Object ... args){
		
		return null;
	}
	
	
}

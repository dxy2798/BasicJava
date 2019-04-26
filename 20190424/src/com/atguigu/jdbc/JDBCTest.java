package com.atguigu.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

public class JDBCTest {
	
	@Test
	public void testResultSetMetaData(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try{
			String sql = "SELECT flow_id flowid,type,id_card idCard," 
					+ "exam_card examCard,student_name studentName,"
					+"location,grade " + "FROM examstudent WHERE flow_id = ?";
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, 2);
			resultSet = preparedStatement.executeQuery();
			Map<String, Object> values = new HashMap<String, Object>();
			while(resultSet.next()){
				ResultSetMetaData rsmd = resultSet.getMetaData();
				for(int i = 0;i<rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);
					values.put(columnLabel, columnValue);
				}
			}
			
			Class clazz = Student.class;
			Object object = clazz.newInstance();
			
			for(Map.Entry<String, Object> entry:values.entrySet()){
				String fieldName = entry.getKey();
				Object fieldValue = entry.getValue();

				ReflectionUtils.setFieldValue(object, fieldName, fieldValue);
			}
			System.out.println(object);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		
	}
	/**
	 * 测试通用查询
	 */
	@Test
	public void testGet(){
		String sql = "SELECT id,name,email,birth from customers "
				+ "WHERE id = ?";
		Customer customer = get(Customer.class, sql, 1);
		System.out.println(customer); 
		
		 sql = "SELECT flow_id flowid,type,id_card idCard," 
				+ "exam_card examCard,student_name studentName,"
				+"location,grade " + "FROM examstudent WHERE flow_id = ?";
		Student stu = get(Student.class, sql, 2);
		System.out.println(stu);
	}
	/**
	 * 通用查询方法,可以根据传入的 SQL、Class 对象返回的 SQL 对应的记录的对象
	 * @param clazz: 描述对象的类型
	 * @param sql: SQL 语句。可能带占位符
	 * @param args: 填充占位符的可变参数
	 * @return
	 */
	public <T> T get(Class<T> clazz,String sql,Object ... args){
		T entity = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i + 1, args[i]);
			}

			resultSet = preparedStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			
			Map<String, Object> values = new HashMap<>();
			
			if(resultSet.next()){
				for(int i = 0;i<rsmd.getColumnCount();i++){
					String columnLabel = rsmd.getColumnLabel(i + 1);
					Object columnValue = resultSet.getObject(i + 1);
					values.put(columnLabel, columnValue);
				}
			}
			if(values.size() > 0){
				entity= clazz.newInstance();
				for(Map.Entry<String, Object> entry:values.entrySet()){
					String fieldName = entry.getKey();
					Object value = entry.getValue();
					ReflectionUtils.setFieldValue(entity, fieldName, value);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		
		
		return entity;
	}
	
	public void addNewStudent2(Student student){
		String sql = "INSERT INTO NICK.EXAMSTUDENT(TYPE,ID_CARD,EXAM_CARD,STUDENT_NAME,LOCATION,GRADE) "
				+ " VALUES(?,?,?,?,?,?)";
		
		JDBCTools.update(sql, student.getType(),student.getIdCard(),student.getExamCard(),
				student.getStudentName(),student.getLocation(),student.getGrade());
		
	}
	
	@Test
	public void testPreparedStatement(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO nick.customers (name,email,birth) "
					+ "VALUES(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, "Jerry");
			preparedStatement.setString(2, "Jerry@163.com");
			preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, preparedStatement, connection);
		}
	}

	@Test
	public void testAddNewStudent(){
		Student student = getStudentFromConsole();
		addNewStudent2(student); //改成2是用 PreparedStatement 方式
	}
	/**
	 * 从控制台输入学生信息
	 * @return
	 */
	private Student getStudentFromConsole() {
		
		Scanner scanner = new Scanner(System.in);
		
		Student student = new Student();
		
		System.out.print("Type:");
	    student.setType(scanner.nextInt());
	    
	    System.out.print("idCard:");
	    student.setIdCard(scanner.next()); 
	    
	    System.out.print("examCard:");
	    student.setExamCard(scanner.next());
	    
	    System.out.print("studentName:");
	    student.setStudentName(scanner.next());
	    
	    System.out.print("Location:");
	    student.setLocation(scanner.next());
	    
		System.out.print("Grade:");
	    student.setGrade(scanner.nextInt());
	    
		return student;
	}

	public void addNewStudent(Student student){
		//1.准备SQL语句
		String sql = "INSERT INTO NICK.EXAMSTUDENT(TYPE,ID_CARD,EXAM_CARD,STUDENT_NAME,LOCATION,GRADE)  " 
				+ "Values(" + student.getType() 
				+ ",'" + student.getIdCard() 
				+ "','" + student.getExamCard() 
				+ "','" + student.getStudentName() 
				+ "','" + student.getLocation() 
				+ "'," + student.getGrade() +")";
		//2.调用JDBCTools 的 update(sql) 方法
		JDBCTools.update(sql);
	}
	

}

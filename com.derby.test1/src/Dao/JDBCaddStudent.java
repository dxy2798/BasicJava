package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import org.junit.Test;


public class JDBCaddStudent {
	

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
	 * 取得一个对象
	 */
	@Test
	public void testGetStudent(){
		//1.得到查询的类型
		  int searchType = getSearchTypeFromConsole();
		//2.具体查询学生信息
		  Student student = searchStudent(searchType);
		//3.打印学生信息
		  printStudent(student);
	}
	
	/**
	 * 打印学生信息: 若存在则打印其具体信息，若不存在:打印查无此人
	 * @param student
	 */
	private void printStudent(Student student) {
		if(student != null){
			System.out.println(student);
		}else{
			System.out.println("查无此人！");
		}
		
	}

	/**
	 * 具体查询学生信息，返回一个student 对象，若不存在返回 null
	 * @param searchType
	 * @return
	 */
	private Student searchStudent(int searchType) {
		
		String sql = "SELECT flowid,type,idcard,examcard,"
				+ "studentname,location,grade "
				+ "FROM nick.examstudent "
				+ "WHERE ";
		Scanner scanner = new Scanner(System.in);
		// 1.根据输入的 searchType,提示用户输入信息
		//   若为1,提示输入准考证号,若为2,提示输入准考证号
		// 2.根据searchType 确定SQL
		if(searchType == 1){
			System.out.print("请输入准考证号：");
			String examCard = scanner.next();
			sql = sql + "examcard = '" + examCard + "'";
			
		}else{
			System.out.print("请输入身份证号：");
			String idCard = scanner.next();
			sql = sql + "idcard = '" + idCard + "'";			
		}
		// 3.执行查询
		Student student = getStudent(sql);
		// 4.若存在查询结果,把查询结果封装为一个Student对象

		return student;
	}

	/**
	 * 根据传入的sql 返回Student 对象
	 * @param sql
	 * @return
	 */
	private Student getStudent(String sql) {
		Student student = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if(resultSet.next()){
				int flowId = resultSet.getInt(1);
				int type = resultSet.getInt(2);
				String idCard = resultSet.getString(3);
				String examCard = resultSet.getString(4);
				String studentName = resultSet.getString(5);
				String location = resultSet.getString(6);
				int grade = resultSet.getInt(7);
				
				student = new Student(flowId,type, idCard, examCard, studentName, location, grade);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, statement, connection);
		}
		return student;
	}

	/**
	 * 从控制台读入一个整数，确定查询的类型。
	 * @return: 1.用身份证查询。2.用准考证查询 其他无效.并提示请用户重新输入
	 */
	@Test
	private int getSearchTypeFromConsole() {
		System.out.print("请输入查询类型:1.准考证查询 2.身份证查询");
		Scanner scanner = new Scanner(System.in);
		int type = scanner.nextInt();
		if(type !=1 && type != 2){
			System.out.println("输入有误请重新输入！");
			throw new RuntimeException();
		}
		return type;
	}



	/**
	 * 从控制台添加一个student对象，insert into 数据库
	 */
	@Test
	public void testAddNewStudent(){
		Student student = getStudentFromConsole();
		addNewStudent2(student);
	}

	private Student getStudentFromConsole() {
		Scanner scanner = new Scanner(System.in);
		Student student = new Student();

		System.out.println("Type:");
		student.setType(scanner.nextInt());
		
		System.out.println("IdCard:");
		student.setIdCard(scanner.next());
		
		System.out.println("ExamCard:");
		student.setExamCard(scanner.next());
		
		System.out.println("StudentName:");
		student.setStudentName(scanner.next());		

		System.out.println("Location:");
		student.setLocation(scanner.next());	
		
		System.out.println("Grade:");
		student.setGrade(scanner.nextInt());		
		
		return student;
	}

	public void addNewStudent(Student student){
		String sql = "INSERT INTO NICK.EXAMSTUDENT(TYPE,IDCARD,EXAMCARD,STUDENTNAME,LOCATION,GRADE)  " 
				+ "Values(" + student.getType() 
				+ ",'" + student.getIdCard() 
				+ "','" + student.getExamCard() 
				+ "','" + student.getStudentName() 
				+ "','" + student.getLocation() 
				+ "'," + student.getGrade() +")";
		JDBCTools.update(sql);
	}
	/**
	 * 用 preparedStatement 方式
	 * @param student
	 */
	public void addNewStudent2(Student student){
		String sql = "INSERT INTO nick.examstudent(type,idCard,examCard,StudentName,location,grade)"
				+ " VALUES(?,?,?,?,?,?)";
		JDBCTools.update(sql,student.getType(),student.getIdCard(),student.getExamCard(),student.getStudentName(),
				student.getLocation(),student.getGrade());
	}
	
	/**
	 * SQL注入
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
	
	/**
	 * 使用PreparedStatement 防止SQL注入
	 */
	@Test
	public void testSQLInjection2(){
		String username = "a' OR password = ";
		String password = " OR '1' = '1";
		String sql = "SELECT * FROM nick.users WHERE username = ? AND password = ?";
		System.out.println(sql);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try{
		    connection =JDBCTools.getConnection();
		    preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setString(1, username);
		    preparedStatement.setString(2, password);
		    resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				System.out.println("登录成功!");
			}else{
				System.out.println("用户名和密码不匹配或用户名不存在!");
			}
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
	}
	@Test
	public Student getStudent2(String sql, Object ... args){
		Student student = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				preparedStatement.setObject(i + 1 , args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				student = new Student();
				student.setFlowid(resultSet.getInt(1));
				student.setType(resultSet.getInt(2));
				student.setIdCard(resultSet.getString(3));;
				student.setExamCard(resultSet.getString(4));;
				student.setStudentName(resultSet.getString(5));;
				student.setLocation(resultSet.getString(6));
				student.setGrade(resultSet.getInt(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		return student;
	}
	

	
	
	@Test
	public void testGet(){
		String sql = "SELECT id,name,email,birth "
				+ "FROM nick.customers WHERE id = ?";
		Customer customer = get(Customer.class, sql, 5);
		System.out.println(customer);
		
		sql = "Select flow_Id flowid,id_Card idCard,exam_Card examCard,student_Name studentName, "
				+ "location,grade From nick.examstudent WHERE flow_Id = ?";
		Student stu = get(Student.class, sql, 3);
		System.out.println(stu);
	}
	
	/**
	 * 测试 ResultSetMetaData 方法
	 */
	@Test
	public void testResultSetMetaData(){
		
	}
	/**
	 * 通用查询方法试写
	 */
	public <T> T get(Class<T> clazz,String sql,Object ... args){
		T entity = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i = 0;i<args.length;i++){
				preparedStatement.setObject(i + 1 , args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				//利用反射创建对象
				entity = clazz.newInstance();
				//通过解析sql语句，判断选择了哪些列。以及需要为entity的哪些属性赋值
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCTools.release(resultSet, preparedStatement, connection);
		}
		
		
		return entity;
	}
	

}

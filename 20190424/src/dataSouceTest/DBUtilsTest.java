package dataSouceTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;



/**
 * 测试 DBUtils 工具类
 * @author stephen
 *
 */
public class DBUtilsTest {
	
	//1. 创建 QueryRunner 的实现类
	QueryRunner queryRunner = new QueryRunner();
	
	@SuppressWarnings("rawtypes")
	class MyResultSetHandler implements ResultSetHandler{

		@Override
		public Object handle(ResultSet resultSet) 
				throws SQLException {
			
			List<Customer> customers = new ArrayList<>();
			
			while(resultSet.next()){
				Integer id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				Date birth = resultSet.getDate(4);
				
				Customer customer = 
						new Customer(id, name, email, birth);
				customers.add(customer);
			}
			return customers;
		}
	}
	
	/**
	 * ScalarHandler: 把结果集转为一个数值(可以是任意基本数据类型和字符串)
	 */
	@Test
	public void testScalarHandler(){
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			/*String sql = "SELECT name "
					+ "FROM ajax.customers WHERE id = ?";*/
			String sql = "SELECT count(id) "
					+ "FROM ajax.customers";
			Object result = queryRunner.query(connection, 
					sql, new ScalarHandler());
			
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}
	
	/**
	 * MapListHandler: 将结果集转为一个 Map 的 List
	 * Map 对应查询的一条记录 键: SQL 查询的列名(不是列的别名),值:列的值
	 * MapListHandler 返回的多条记录对应的 Map 的集合
	 */
	@Test
	public void testMapListHandler(){
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name,email,birth "
					+ "FROM ajax.customers WHERE id = 5";
			List<Map<String, Object>> result = queryRunner.query(connection, 
					sql, new MapListHandler());
			
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}
	
	
	
	/**
	 * MapHandler:返回 SQL 对应的第一条记录对应的 Map 对象.
	 * 键: SQL 查询的列名(不是列的别名),值:列的值
	 */
	@Test
	public void testMapHandler(){
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name,email,birth "
					+ "FROM ajax.customers";
			Map<String, Object> result = queryRunner.query(connection, 
					sql, new MapHandler());
			
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}
	
	
	
	
	/**
	 * BeanListHandler:把结果集转为 List , 该  List 不为 Null 但可能是
	 * 空集合(size() 方法返回 0), 若 SQL 语句的确能够查询到记录, List 中
	 * 存放创建 BeanListHandler 传入的 Class 对象对应的对象。
	 */
	@Test
	public void testBeanListHandler() {
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name,email,birth "
					+ "FROM ajax.customers";
			List<Customer> customers = (List<Customer>) queryRunner.query(connection, 
					sql, new BeanListHandler(Customer.class));
			
			System.out.println(customers);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}

	}

/**
 * BeanHandle
 * 把结果集的第一条记录转为创建 BeanHandler 对象时传入的 Class
 * 参数对应的对象。
 */
@Test
public void testBeanHandler(){
	Connection connection = null;
	
	try{
		connection = JDBCTools.getConnection();
		String sql = "SELECT id,name,email,birth "
				+ "FROM ajax.customers WHERE id = ?";
		Customer customer = (Customer) queryRunner.query(connection, 
				sql, new BeanHandler(Customer.class), 5);
		System.out.println(customer);
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		JDBCTools.release(null, null, connection);
	}
}


	/**
	 * QueryRunner 的 query 方法的返回值取决于其 ResultSetHandler 参数的
	 * handle 方法的返回值
	 */
	@Test
	public void testQuery(){
		
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name,email,birth FROM ajax.customers";
			
			Object obj = 
					queryRunner.query(connection, sql, new MyResultSetHandler());
			
			System.out.println(obj);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
		
		
		
		
		
	}
	
	
	/**
	 * 测试 QueryRunner 类的 update 方法
	 * 该方法可用于 INSERT,UPDATE 和 DELETE
	 */
	@Test
	public void testQueryRunnerUpdate() {
		
		
			
		//2. 使用其 update 方法
			String sql = "DELETE FROM ajax.customers "
					+ "WHERE id IN (?,?)";
			Connection conn = null;
			
			try{
				conn = JDBCTools.getConnection();
				queryRunner.update(conn, sql,1,2);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				JDBCTools.release(null, null, conn);
			}
	}
}

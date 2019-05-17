package dataSouceTest;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import com.mchange.v2.c3p0.impl.NewPooledConnection;

public class CustomerDaoTest {

	CustomerDao customerDao = new CustomerDao();

	@Test
	public void testBatch() {
		Connection connection = null;
		try{
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO ajax.customers(name,email,birth) VALUES(?,?,?)";
			//PreparedStatement preparedStatement = connection.prepareStatement(sql);
			Object[][] param= new Object[10][3];
			for(int i=0; i < 10; i++){
				param[i]= new Object[3];
				param[i][0] = "name_"+i;
				param[i][1] = "email_"+i;
				param[i][2] = new Date(new java.util.Date().getTime());
			}
			customerDao.batch(connection, sql, param);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			JDBCTools.release(null, null, connection);
		}
		
	}

	@Test
	public void testGetForValue() {
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT count(id) "
					+ "FROM ajax.customers";
			Object result = customerDao.getForValue(connection, 
					sql);
			
			System.out.println(result);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}

	@Test
	public void testGetForList() {
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name,email,birth FROM ajax.customers";
			List<Customer> list = new ArrayList<>();
			
			list = customerDao.getForList(connection, sql);
			
			System.out.println(list);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}

	@Test
	public void testGet() {
		Connection connection = null;
		
		try{
			connection = JDBCTools.getConnection();
			String sql = "SELECT id,name CustomerName,"
					+ "email,birth FROM ajax.customers "
					+ "WHERE id = ?";
			Customer customer = customerDao.get(connection,sql,5);
			
			System.out.println(customer);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}

	@Test
	public void testUpdate() {
		Connection connection = null;
		try{
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO ajax.customers(name,email,birth) VALUE(?,?,?)";
			
			customerDao.update(connection, sql, "Tom","tom@163.com",new Date(new java.util.Date().getTime()));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.release(null, null, connection);
		}
	}

}
